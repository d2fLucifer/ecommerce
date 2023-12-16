package com.lucifer.ecommerce.Service;

import com.lucifer.ecommerce.dto.VariationDto;

import java.util.List;

public interface VariationService {
    VariationDto createVariation(VariationDto variationDto);

    VariationDto updateVariation(VariationDto variationDto, Long id);

    void deleteVariationById(Long id);

    List<VariationDto> findAllVariation();

    VariationDto findVariationById(Long id);

}
