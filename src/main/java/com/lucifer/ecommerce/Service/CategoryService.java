package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.CategoryDto;
import com.lucifer.ecommerce.dto.Response.CategoryResponse;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryResponse findAllCategory();
    CategoryDto updateCategory(String id);
    void deleteById(String id);

}
