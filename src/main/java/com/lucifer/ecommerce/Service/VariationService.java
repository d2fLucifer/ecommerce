package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.VariationDto;
import com.lucifer.ecommerce.model.Variation;

public interface VariationService {
    VariationDto createVariation(VariationDto variationDto);

    VariationDto updateVariation(VariationDto variationDto, String id);

    void deleteVariationById(String id);

    VariationDto findAllVariation();

    VariationDto findVariationById(String id);

}
