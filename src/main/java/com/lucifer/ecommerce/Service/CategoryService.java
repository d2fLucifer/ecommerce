package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CategoryDto;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto findAllCategory();
    CategoryDto updateCategory(String id);
    void deleteById(String id);

}
