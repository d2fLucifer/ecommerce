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
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rating;
    @Column(columnDefinition = "TEXT")
    @Lob
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date_created")
    private Date date;
    @Column(name = "last_updated")
    private Date UpdatedDate;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @ManyToMany(mappedBy = "reviews")

    private List<Product> products;









}
