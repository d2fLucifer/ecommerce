package com.lucifer.ecommerce.Service.Impl;

import com.lucifer.ecommerce.Service.VariationService;
import com.lucifer.ecommerce.dto.VariationDto;
import com.lucifer.ecommerce.exception.ResourceAlreadyExitException;
import com.lucifer.ecommerce.exception.ResourceNotFoundException;
import com.lucifer.ecommerce.model.Product;
import com.lucifer.ecommerce.model.Variation;
import com.lucifer.ecommerce.repository.ProductRepository;
import com.lucifer.ecommerce.repository.VariationRepository;
import com.lucifer.ecommerce.utils.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VariationServiceImpl implements VariationService {

    private final VariationRepository variationRepository;
    private final GenericMapper genericMapper;
    private final ProductRepository productRepository;

    @Override
    public VariationDto createVariation(VariationDto variationDto) {
        Optional<Variation> exitVariations = variationRepository.findByVariationOption(variationDto.getVariationOption());

        if (exitVariations.isEmpty()) {
            System.out.println(variationDto);
            Variation savedVariation = variationRepository.save(genericMapper.map(variationDto, Variation.class));
            return genericMapper.map(savedVariation, VariationDto.class);
        } else
            throw new ResourceAlreadyExitException("Variation option", "name", variationDto.getVariationOption());

    }

    @Override
    public VariationDto updateVariation(VariationDto variationDto, Long id) {
        Variation variation = variationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Variation ", "id", id));
        variation.setVariationOption(variationDto.getVariationOption());
        variation.setValue(variationDto.getValue());


        return genericMapper.map(variationRepository.save(variation), VariationDto.class);
    }

    @Override
    public void deleteVariationById(Long id) {
        Variation variation = variationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Variation ", "id", id));
        List<Product> products = productRepository.findByVariationsId(id).orElse(null);
        products.forEach(product -> product.getVariations().removeIf(variation1 -> variation1.getId().equals(id)));
        productRepository.saveAll(products);
        variationRepository.deleteById(id);
    }

    @Override
    public List<VariationDto> findAllVariation() {
        return
                genericMapper.mapList(variationRepository.findAll(), VariationDto.class);
    }

    @Override
    public VariationDto findVariationById(Long id) {
        Variation variation = variationRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Variation ", "id", id));

        return genericMapper.map(variation, VariationDto.class);
    }
}
