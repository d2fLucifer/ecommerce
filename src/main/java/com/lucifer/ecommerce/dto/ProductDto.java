package com.lucifer.ecommerce.dto;

import com.lucifer.ecommerce.model.Variation;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Price should not be empty")
    private long price;
    @NotEmpty(message = "Quantity of products  should not be empty")
    private double quantity;
    @NotEmpty(message = "Category should not be empty")
    private String category;

    private List<Variation> variations;


}
