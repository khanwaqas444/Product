package com.product_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer originalPrice;
    private Integer discountPercent;
    private Double rating;
    private Boolean onSale;
    private String image;
}
