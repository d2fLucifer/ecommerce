package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.dto.Response.ProductResponse;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, Long categoryId);

    ProductDto updateProduct(ProductDto productDto, Long id);

    void deleteProductById(Long id);

    ProductDto getProductById(Long id);

    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
}
