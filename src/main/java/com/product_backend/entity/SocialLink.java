package com.product_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String platform;
    private String url;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
}
