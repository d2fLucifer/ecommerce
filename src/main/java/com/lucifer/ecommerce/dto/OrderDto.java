package com.lucifer.ecommerce.dto;

import com.lucifer.ecommerce.model.PaymentMethod;
import com.lucifer.ecommerce.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String firstName;
    private String lastName;
    private String email;
    private Date orderDate;
    private Status status;
    private PaymentMethod paymentMethod;
}
