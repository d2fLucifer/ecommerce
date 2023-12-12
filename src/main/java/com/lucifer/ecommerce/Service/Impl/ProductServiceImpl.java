package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.ProductService;
import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.dto.Response.ProductResponse;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Category;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.Variation;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        // Map the ProductDto to a Product entity
        Product product = genericMapper.map(productDto, Product.class);

        // Map and set the variations if they are not null in productDto
        if (productDto.getVariations() != null) {
            List<Variation> variations = genericMapper.mapList(productDto.getVariations(), Variation.class);
            product.setVariations(variations);
        }

        // Map and set the category if it's not null in productDto
        if (productDto.getCategory() != null) {
            Category category = genericMapper.map(productDto.getCategory(), Category.class);
            product.setCategory(category);
        }

        // Save the product
        Product savedProduct = productRepository.save(product);

        // Map and return the saved product as ProductDto
        return genericMapper.map(savedProduct, ProductDto.class);
    }


    @Override
    public ProductDto updateProduct(ProductDto productDto, String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(product.getPrice());
        product.setQuantityInStock(productDto.getQuantityInStock());
        product.setCategory(genericMapper.map(productDto.getCategory(), Category.class));
        product.setImage(productDto.getImage());
        if (productDto.getVariations() != null)
            product.setVariations(genericMapper.mapList(productDto.getVariations(), Variation.class));

        Product updatedProduct = productRepository.save(product);

        return genericMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public void deleteProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)
        );
        productRepository.delete(product);

    }

    @Override
    public ProductDto getProductById(String id) {
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
