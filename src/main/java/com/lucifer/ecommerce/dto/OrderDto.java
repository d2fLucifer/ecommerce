package com.lucifer.ecommerce.dto;

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
    @NotEmpty(message = "userId should not bn empty")
    private String userId;
    @NotEmpty(message = "Products should not be empty ")
    private List<ProductDto> products;


}
