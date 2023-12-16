package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Cart;
import com.lucifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    Optional<Cart> findCartByUserId(Long id);


    Optional<Cart> findByUserId(Long userId);
}
