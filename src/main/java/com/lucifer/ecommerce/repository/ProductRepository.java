package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findByCategoryId(Long id);

    Optional<List<Product>> findByVariationsId(Long id);

    Optional<List<Product>> findByIdIn(List<Long> ids);

}
