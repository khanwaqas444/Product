package com.product_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String whatsapp;
    private String email;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}

