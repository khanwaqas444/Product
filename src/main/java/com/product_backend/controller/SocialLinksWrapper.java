package com.product_backend.controller;

import com.product_backend.entity.SocialLink;
import lombok.Data;

import java.util.List;

@Data
public class SocialLinksWrapper {
    private List<SocialLink> socialLinks;
}
