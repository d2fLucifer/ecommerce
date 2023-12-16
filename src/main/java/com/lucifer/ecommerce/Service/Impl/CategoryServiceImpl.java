package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.CategoryService;
import com.lucifer.ecommerce.dto.CategoryDto;
import com.lucifer.ecommerce.exception.ResourceAlreadyExitException;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Category;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.repository.CategoryRepository;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final GenericMapper genericMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Optional<Category> category1 = categoryRepository.findByName(categoryDto.getName());
        if (category1.isPresent())
            throw new ResourceAlreadyExitException("Category", "name ", categoryDto.getName());

        categoryDto.setName(categoryDto.getName().toUpperCase());
        Category category = categoryRepository.save(genericMapper.map(categoryDto, Category.class));
        return genericMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        return (List<CategoryDto>) genericMapper.mapList(categoryRepository.findAll(), CategoryDto.class);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        category.setName(categoryDto.getName().toUpperCase());

        Category savedCategory = categoryRepository.save(category);
        return genericMapper.map(savedCategory, CategoryDto.class);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        List<Product> products = productRepository.findByCategoryId(id).orElse(null);
        for (Product product : products) {
            product.setCategory(null);
        }

        productRepository.saveAll(products);

        categoryRepository.delete(category);
    }


    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        return genericMapper.map(category, CategoryDto.class);
    }
}
