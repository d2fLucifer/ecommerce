package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
 List <CategoryDto> findAllCategory();
    CategoryDto updateCategory(Long id , CategoryDto categoryDto);
    void deleteById(Long id);
    CategoryDto findById(Long id);

}
