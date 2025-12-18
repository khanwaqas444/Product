package com.product_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLinkDto {

    private String platform;
    private String url;
}