package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByPaymentMethod(String paymentMethod);

}
