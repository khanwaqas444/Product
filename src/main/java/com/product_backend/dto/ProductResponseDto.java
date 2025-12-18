package com.product_backend.dto;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String _id;
    private String name;
    private double price;
    private double originalPrice;
    private double rating;
    private int reviews;
    private String image;
    private String category;
    private Boolean sale;
    private String badge;
    private String sellerId;
}
