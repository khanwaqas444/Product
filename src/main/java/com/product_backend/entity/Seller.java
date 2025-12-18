package com.product_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seller")
public class Seller {

    @Id
    @Column(length = 36)
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

    // ================= CONTACT =================
    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Contact contact;

    // ================= SOCIAL LINKS =================
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SocialLink> socialLinks;
}
