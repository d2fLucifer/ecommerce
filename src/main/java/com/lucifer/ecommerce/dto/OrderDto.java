package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucifer.ecommerce.model.Status;
import com.lucifer.ecommerce.model.User;
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
    private UserDto userDto;

    @NotEmpty(message = "Products should not be empty ")
    private List<ProductDto> products;

    @JsonProperty("payment_method")
    @NotEmpty(message = "Payment method  should not be empty ")
    private Long paymentId;


    private Status status;



    public UserDto getUserDto() {
        if (userDto == null) {
            userDto = new UserDto(); // Instantiate a new UserDto if it is null
        }
        return userDto;
    }
}
