package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.PaymentDTO;
import com.lucifer.ecommerce.model.Payment;

import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO updatePayment(PaymentDTO paymentDTO, Long paymentId);

    void deletePayment(Long paymentId);

    PaymentDTO getPaymentById(Long id);

    List<PaymentDTO> findAllPayments();
}
