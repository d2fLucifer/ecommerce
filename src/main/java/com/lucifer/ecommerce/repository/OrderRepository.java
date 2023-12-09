package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.dto.OrderDto;
import com.lucifer.ecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT new com.lucifer.ecommerce.dto.OrderDto(o.user.firstName, o.user.lastName, o.user.email, o.orderDate, o.status, o.payment.paymentMethod) " +
            "FROM Order o WHERE o.user.id = :id")
    Optional<OrderDto> findOrderInformationByUserId(@Param("id") String userId);


    @Query(value = "SELECT new com.lucifer.ecommerce.dto.OrderDto(o.user.firstName, o.user.lastName, o.user.email, o.orderDate, o.status, o.payment.paymentMethod) " +
            "FROM Order o", nativeQuery = true)
    List<OrderDto> findAllOrderInformation();


}
