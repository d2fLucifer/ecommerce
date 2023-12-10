package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.dto.Response.ProductResponse;
import com.lucifer.ecommerce.model.Product;

public interface ProductService {
    ProductDto createProduct(Product product);

    ProductDto updateProduct(ProductDto productDto, String id);

    void deleteProductById(String id);

    ProductResponse getProductById(String id);

    ProductResponse getAllProducts();
}
