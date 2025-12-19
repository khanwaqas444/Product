package com.product_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Data
public class Seller {

    @Id
    private String id;

    private String name;
    private String handle;
    private String description;
    private String avatarUrl;
    private String location;
    private double overallRating;
    private int reviewCount;
    private boolean freeShipping;
    private boolean verified;
    private boolean onlineStatus;

    // 🔥 MUST BE EXACT
    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL)
    private Contact contact;

    // 🔥 MUST BE EXACT
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<SocialLink> socialLinks;

    // 🔥 ONLY FOR FORM-DATA (NOT DB)
    @Transient
    private MultipartFile avatar;
}
