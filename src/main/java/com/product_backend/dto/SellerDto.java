package com.product_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerDto {
    private String name;
    private String username;
    private String bio;
    private String location;
    private Double rating;
    private Integer totalReviews;
    private Boolean freeShipping;
    private String profileImage;
}
