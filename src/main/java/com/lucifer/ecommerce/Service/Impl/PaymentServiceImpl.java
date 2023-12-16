package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.PaymentService;
import com.lucifer.ecommerce.dto.PaymentDTO;
import com.lucifer.ecommerce.exception.ResourceAlreadyExitException;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Order;
import com.lucifer.ecommerce.model.Payment;
import com.lucifer.ecommerce.repository.OrderRepository;
import com.lucifer.ecommerce.repository.PaymentRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    private final GenericMapper genericMapper;

    @Override
    @Transactional
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Optional<Payment> payment = paymentRepository.findByPaymentMethod(paymentDTO.getPaymentMethod().toUpperCase());
        if (payment.isPresent())
            throw new ResourceAlreadyExitException("Payment method", "name", paymentDTO.getPaymentMethod());
        paymentDTO.setPaymentMethod(paymentDTO.getPaymentMethod().toUpperCase());

        return genericMapper.map(paymentRepository.save(genericMapper.map(paymentDTO, Payment.class)), PaymentDTO.class);
    }
    @Transactional

    @Override
    public PaymentDTO updatePayment(PaymentDTO paymentDTO, Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment ", "id", paymentId));
        Optional<Payment> exitPayment = paymentRepository.findByPaymentMethod(paymentDTO.getPaymentMethod().toUpperCase());

        if (exitPayment.isPresent())
            throw new ResourceAlreadyExitException("Payment method", "name", paymentDTO.getPaymentMethod());

        payment.setPaymentMethod(paymentDTO.getPaymentMethod());


        return genericMapper.map(paymentRepository.save(payment), PaymentDTO.class);
    }
    @Transactional

    @Override
    public void deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment ", "id", paymentId));
        List<Order> orders = payment.getOrders();
        orders.removeIf(deletedPayment -> deletedPayment.getId().equals(paymentId));
        orderRepository.saveAll(orders);
        paymentRepository.delete(payment);
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment ", "id", id));
        return genericMapper.map(payment, PaymentDTO.class);
    }

    @Override
    public List<PaymentDTO> findAllPayments() {
        return genericMapper.mapList(paymentRepository.findAll(), PaymentDTO.class);
    }
}
