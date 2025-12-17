package com.product_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private Integer originalPrice;
    private Integer discountPercent;
    private Double rating;
    private Boolean onSale;
    private String imageUrl;

    @ManyToOne
    private Seller seller;

    @ManyToOne
    private Category category;

    private LocalDateTime createdAt;
}
