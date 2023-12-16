package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    @JsonProperty("first_name")
    @NotEmpty(message = "first name should not be empty")

    private String firstname;
    @JsonProperty("last_name")
    @NotEmpty(message = "last name  should not be empty")

    private String lastname;
    @NotEmpty(message = "mobilephone  should not be empty")


    private String mobilephone;
    @NotEmpty(message = "email should not be empty")

    private String email;
    @NotEmpty(message = "password should not be empty")

    private String password;
    private String role;
}
