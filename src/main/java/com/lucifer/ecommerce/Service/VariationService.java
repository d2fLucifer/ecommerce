package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.Response.VariationResponse;
import com.lucifer.ecommerce.dto.VariationDto;

public interface VariationService {
    VariationDto createVariation(VariationDto variationDto);

    VariationDto updateVariation(VariationDto variationDto, String id);

    void deleteVariationById(String id);

    VariationResponse findAllVariation();

    VariationResponse findVariationById(String id);

}
