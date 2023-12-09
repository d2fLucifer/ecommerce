package com.lucifer.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "variation")
public class Variation {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;
    @Column(name = "variation_option")
    private String variationOption;
    @ManyToMany
    @JoinTable(name = "product_variation",
            joinColumns = @JoinColumn(name = "variation_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
  private   List<Product> products;
}
