package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> finAll();

    Optional<User> findUserById(String id);

    Optional<User> findByEmail(String email);



}
