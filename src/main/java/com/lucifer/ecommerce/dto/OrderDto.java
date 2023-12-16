package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucifer.ecommerce.model.Status;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Long userId;

    @NotEmpty(message = "Products should not be empty ")
    private List<ProductDto> products;

    @JsonProperty("payment_method")
    @NotEmpty(message = "Payment method  should not be empty ")
    private Long paymentId;


    private Status status;
}
