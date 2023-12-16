package com.lucifer.ecommerce.repository;

import com.lucifer.ecommerce.model.Variation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface VariationRepository extends JpaRepository<Variation, Long> {
    Optional<Variation> findByVariationOption(String variationOption);

}
