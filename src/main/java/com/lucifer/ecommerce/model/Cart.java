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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private User user;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "carts")

    private List<Product> products;
    @Column(name = "last_updated")
    private Date lastUpdated;

}
