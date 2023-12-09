package com.lucifer.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

   private String city;
    private   String country;
    @Column(name = "detail_address")
    @JsonProperty(namespace = "detail_address")
    private  String detailAddress;
    private  String street;



}
