package com.lucifer.ecommerce.controller;

import com.lucifer.ecommerce.Service.PaymentService;
import com.lucifer.ecommerce.dto.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentService.createPayment(paymentDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@RequestBody PaymentDTO paymentDTO, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(paymentService.updatePayment(paymentDTO, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable(name = "id") Long id) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
    }

    // If you have a method to get all payments, you can add it here
    // @GetMapping
    @GetMapping
     public ResponseEntity<List<PaymentDTO>> getAllPayments() {
         return new ResponseEntity<>(paymentService.findAllPayments(), HttpStatus.OK);
     }
}
