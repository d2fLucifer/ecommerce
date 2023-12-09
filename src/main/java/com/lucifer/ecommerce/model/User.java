package com.lucifer.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private String id;

    private String email;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    String password;

    @Column(name = "mobile_phone")
    @JsonProperty("mobile_phone")
    private long mobilephone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

}
