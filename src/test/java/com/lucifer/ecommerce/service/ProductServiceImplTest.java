package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.ProductService;
import com.lucifer.ecommerce.dto.ProductDto;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private GenericMapper genericMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductDto productDto;
    private Product product;

    @BeforeEach
    void setUp() {
        productDto = new ProductDto(); // set properties
        product = new Product(); // set properties
    }

    @Test
    void createProductTest() {
        when(genericMapper.map(productDto, Product.class)).thenReturn(product);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(genericMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto savedProductDto = productService.createProduct(productDto);

        assertNotNull(savedProductDto);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void updateProductTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(genericMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto updatedProductDto = productService.updateProduct(productDto, "some-id");

        assertNotNull(updatedProductDto);
        verify(productRepository).save(any(Product.class));
    }

    @Test
    void deleteProductByIdTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProductById("some-id");

        verify(productRepository).delete(any(Product.class));
    }

    @Test
    void getProductByIdTest() {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        when(genericMapper.map(product, ProductDto.class)).thenReturn(productDto);

        ProductDto foundProductDto = productService.getProductById("some-id");

        assertNotNull(foundProductDto);
        verify(productRepository).findById(anyString());
    }

    @Test
    void getAllProductsTest() {
        Page<Product> productPage = new PageImpl<>(Collections.singletonList(product));
        when(productRepository.findAll(any(PageRequest.class))).thenReturn(productPage);
        when(genericMapper.mapList(anyList(), eq(ProductDto.class))).thenReturn(Collections.singletonList(productDto));

        // Add your assertion and verification logic here

    }

    // Additional tests for exception handling and edge cases can be added here.
}
