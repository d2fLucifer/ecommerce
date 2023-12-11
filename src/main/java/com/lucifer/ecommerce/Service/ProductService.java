package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.dto.Response.ProductResponse;
import com.lucifer.ecommerce.model.Product;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(ProductDto productDto, String id);

    void deleteProductById(String id);

    ProductDto getProductById(String id);

    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
}
