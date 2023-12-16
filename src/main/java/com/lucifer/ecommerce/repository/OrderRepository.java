package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select o from Order o where o.user.id = :userId")
    Optional<List<Order>> findByUserId(Long userId);

    void deleteById(Long id);


}
