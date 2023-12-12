package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lucifer.ecommerce.model.Category;
import com.lucifer.ecommerce.model.Variation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Price should not be empty")
    private long price;

    @NotEmpty(message = "Description should not be empty")
    private String description;

    @NotNull(message = "Quantity of products  should not be empty")
    @JsonProperty("quantity_in_stock")
    private double quantityInStock;

    @NotNull(message = "Image of products  should not be empty")
   private   String image;

    private CategoryDto category;

    private List<VariationDto> variations;


}
