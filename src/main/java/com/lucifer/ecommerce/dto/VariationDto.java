package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationDto {
    private String id;

    @Override
    public String toString() {
        return "VariationDto{" +
                "id='" + id + '\'' +
                ", variationOption='" + variationOption + '\'' +
                '}';
    }

    @JsonProperty("variation_option")
    @NotEmpty(message = "Variation should not be empty")
    private String variationOption;

    @NotEmpty(message = "Variation value should not be empty")
    private String value;

}
