package com.lucifer.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long id;
    @NotEmpty(message = "Role should not be empty")
    private String role;
    private List<UserDto> userDtos;

    public List<UserDto> getUserDtos() {
        if (userDtos == null) {
            userDtos = new ArrayList<>();
        }
        return userDtos;
    }
}
