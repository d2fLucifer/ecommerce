package com.lucifer.ecommerce.dto.Response;

import com.lucifer.ecommerce.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private String firstName;
    private String lastName;
    private String email;
    private Date orderDate;
    private Status status; // Assuming status is a String. Change the type if needed.
    private String paymentMethod;
}
