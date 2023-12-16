package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Cart;
import com.lucifer.ecommerce.model.Role;
import com.lucifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<List<User>> findByRole(Role role);

    Optional<User> findByCart(Cart cart);


}
