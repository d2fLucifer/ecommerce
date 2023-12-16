package com.lucifer.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;

    @Size(min = 10, message = "Comment  body must be minimum 10 characters")
    @NotEmpty(message = "Content should not be empty")
    private String body;
    @NotEmpty(message = "You should rate products before submit ")
    private int rating;
    private Date date;
    private UserDto user;
    private Date UpdatedDate;
}
