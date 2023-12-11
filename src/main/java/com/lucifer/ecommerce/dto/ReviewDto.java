package com.lucifer.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private String reviewId;
    @NotEmpty(message = "Product id  should not be empty")
    private String productId;
    @NotEmpty(message = "Content should not be empty")
    private String body;
    @NotEmpty(message = "You should rate products before submit ")
    private int rating;
    @NotEmpty(message = "UserId  should not be empty")
    private String userId;

}
