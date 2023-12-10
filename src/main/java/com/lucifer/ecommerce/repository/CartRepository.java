package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
