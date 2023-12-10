package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findAll();

    Optional<User> findUserById(String id);

    Optional<User> findByEmail(String email);



}
