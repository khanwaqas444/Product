package com.product_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SellerResponseDto {

    private String id;
    private String name;
    private String handle;
    private String description;
    private String avatarUrl;
    private String location;

    private Double overallRating;
    private Integer reviewCount;

    private Boolean freeShipping;
    private Boolean verified;
    private Boolean onlineStatus;

    private ContactDto contact;
    private List<SocialLinkDto> socialLinks;
}
