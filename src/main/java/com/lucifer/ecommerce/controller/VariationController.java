package com.lucifer.ecommerce.controller;

import com.lucifer.ecommerce.Service.VariationService;
import com.lucifer.ecommerce.dto.VariationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/variations")
public class VariationController {
    private final VariationService variationService;

    @PostMapping
    public ResponseEntity<VariationDto> createRole(@RequestBody VariationDto variationDto) {
        return new ResponseEntity<>(variationService.createVariation(variationDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VariationDto> updateRole(@RequestBody VariationDto variationDto, @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(variationService.updateVariation(variationDto, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariationDto> getRoleById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(variationService.findVariationById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {

        variationService.deleteVariationById(id);

        return new ResponseEntity<>("Variations deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VariationDto>> getAllRoles() {
        return new ResponseEntity<>(variationService.findAllVariation(), HttpStatus.OK);
    }


}
