package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "street name must be at least 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "building name must be at least 5 character")
    private String building;

    @NotBlank
    @Size(min = 4, message = "city name must be at least 5 character")
    private String city;

    @NotBlank
    @Size(min = 2, message = "country name must be at least 5 character")
    private String country;

    @NotBlank
    @Size(min = 6, max = 6, message = "pin code no is of 6 digit")
    private String pincode;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String building, String city, String country, String pincode) {
        this.street = street;
        this.building = building;
        this.city = city;
        this.country = country;
        this.pincode = pincode;
    }
}
