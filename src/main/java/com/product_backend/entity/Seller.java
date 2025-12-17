package com.product_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String username;
    private String bio;
    private String location;
    private Double rating;
    private Integer totalReviews;
    private Boolean freeShipping;
    private String profileImage;

    private String phone;
    private String whatsapp;
    private String email;
    private String address;
}
