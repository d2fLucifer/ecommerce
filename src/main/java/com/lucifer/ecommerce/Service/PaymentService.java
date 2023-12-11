package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.PaymentDTO;
import com.lucifer.ecommerce.model.Payment;

public interface PaymentService {
    Payment createPayment();

    PaymentDTO updatePayment(PaymentDTO paymentDTO, String paymentId);

    void deletePayment(String paymentId);

    PaymentDTO getPaymentById(String id);
}
