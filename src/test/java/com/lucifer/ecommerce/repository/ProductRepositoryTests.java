package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateAndFindProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("This is a test product");
        product.setPrice(1000L);
        product.setQuantityInStock(10);

        // Save the product
        Product savedProduct = productRepository.save(product);
        // Retrieve the product
        Product foundProduct = productRepository.findById(savedProduct.getId()).orElse(null);

        // Assertions
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getName()).isEqualTo(product.getName());
        assertThat(foundProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(foundProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(foundProduct.getQuantityInStock()).isEqualTo(product.getQuantityInStock());
    }
}
