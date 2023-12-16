package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.ProductService;
import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.dto.Response.ProductResponse;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Category;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.Variation;
import com.lucifer.ecommerce.repository.CategoryRepository;
import com.lucifer.ecommerce.repository.OrderRepository;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.repository.VariationRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final GenericMapper genericMapper;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final VariationRepository variationRepository;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto, Long categoryId) {
        // Convert DTO to entity
        Product product = genericMapper.map(productDto, Product.class);

        // Handle variations
        List<Variation> variations = productDto.getVariations().stream()
                .map(variationDto -> {
                    return variationRepository.findById(variationDto.getId())
                            .orElseGet(() -> genericMapper.map(variationDto, Variation.class));
                })
                .collect(Collectors.toList());
        product.setVariations(variations);

        // Link variations back to the product
        variations.forEach(variation -> variation.getProducts().add(product));

        // Set category
        System.out.println(categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        product.setCategory(category);

        // Save the product (cascades to variations)
        Product savedProduct = productRepository.save(product);

        // Return the saved product as DTO
        return genericMapper.map(savedProduct, ProductDto.class);
    }


    @Override
    @Transactional

    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantityInStock(productDto.getQuantityInStock());
        product.setCategory(genericMapper.map(productDto.getCategory(), Category.class));
        product.setImage(productDto.getImage());
        if (productDto.getVariations() != null)
            product.setVariations(genericMapper.mapList(productDto.getVariations(), Variation.class));

        Product updatedProduct = productRepository.save(product);

        return genericMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    @Transactional


    public void deleteProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        // Handle Category
        Category category = product.getCategory();
        if (category != null) {
            category.getProducts().remove(product);
            categoryRepository.save(category);
        }

        // Handle Variations
        List<Variation> variations = product.getVariations();
        variations.forEach(variation -> variation.getProducts().remove(product));
        variationRepository.saveAll(variations);

        // Handle Orders
        product.getOrders().forEach(order -> order.getProducts().remove(product));
        orderRepository.saveAll(product.getOrders());

        // Clear Variations from Product
        product.getVariations().clear();

        // Delete Product
        productRepository.delete(product);
    }




    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)
        );
        return genericMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Product> products = productRepository.findAll(pageable);
        List<Product> listOfProducts = products.getContent();
        List<ProductDto> productDtos = genericMapper.mapList(listOfProducts, ProductDto.class);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDtos);
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalElements(products.getTotalElements());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setLast(products.isLast());

        return productResponse;
    }

}
