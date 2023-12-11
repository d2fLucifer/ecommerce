package com.lucifer.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;

public class VariationDto {
    private String id;
    @JsonProperty("variation_option")
    @NotEmpty(message = "Variation should not be empty")

    private String variationOption;
}
