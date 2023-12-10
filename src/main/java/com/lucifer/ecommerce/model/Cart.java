package com.lucifer.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;
    @ManyToMany
    @JoinTable(name = "cart_product",
            inverseJoinColumns = @JoinColumn(name = "product_id"),
            joinColumns = @JoinColumn(name = "cart_id"))
    private List<Product> products;
    @Column(name = "last_updated")
    private Date lastUpdated;

}
