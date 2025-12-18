package com.product_backend.controller;

import com.product_backend.dto.ContactDto;
import com.product_backend.dto.SellerResponseDto;
import com.product_backend.dto.SocialLinkDto;
import com.product_backend.entity.Seller;
import com.product_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService service;

    // ================= CREATE (FORM-DATA) =================
    @PostMapping(consumes = "multipart/form-data")
    public SellerResponseDto create(
            @RequestParam String name,
            @RequestParam String handle,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam double overallRating,
            @RequestParam int reviewCount,
            @RequestParam boolean freeShipping,
            @RequestParam boolean verified,
            @RequestParam boolean onlineStatus,

            // 🔥 FILE PARAMETER (MANDATORY FOR FORM-DATA)
            @RequestParam(required = false) MultipartFile avatar
    ) {
        Seller s = new Seller();
        s.setName(name);
        s.setHandle(handle);
        s.setDescription(description);
        s.setLocation(location);
        s.setOverallRating(overallRating);
        s.setReviewCount(reviewCount);
        s.setFreeShipping(freeShipping);
        s.setVerified(verified);
        s.setOnlineStatus(onlineStatus);

        if (avatar != null && !avatar.isEmpty()) {
            s.setAvatarUrl("/uploads/" + avatar.getOriginalFilename());
        }

        return map(service.create(s));
    }


    // ================= UPDATE (FORM-DATA) =================
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public SellerResponseDto update(
            @PathVariable String id,
            @ModelAttribute Seller seller
    ) {
        return map(service.update(id, seller));
    }

    // ================= GET =================
    @GetMapping("/{handle}")
    public SellerResponseDto get(@PathVariable String handle) {
        return map(service.getByHandle(handle));
    }

    // ================= MAPPER =================
    private SellerResponseDto map(Seller s) {
        SellerResponseDto dto = new SellerResponseDto();

        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setHandle(s.getHandle());
        dto.setDescription(s.getDescription());
        dto.setAvatarUrl(s.getAvatarUrl());
        dto.setLocation(s.getLocation());
        dto.setOverallRating(s.getOverallRating());
        dto.setReviewCount(s.getReviewCount());
        dto.setFreeShipping(s.getFreeShipping());
        dto.setVerified(s.getVerified());
        dto.setOnlineStatus(s.getOnlineStatus());

        if (s.getContact() != null) {
            ContactDto c = new ContactDto();
            c.setPhone(s.getContact().getPhone());
            c.setWhatsapp(s.getContact().getWhatsapp());
            c.setEmail(s.getContact().getEmail());
            dto.setContact(c);
        }

        if (s.getSocialLinks() != null) {
            dto.setSocialLinks(
                    s.getSocialLinks().stream()
                            .map(l -> new SocialLinkDto(l.getPlatform(), l.getUrl()))
                            .toList()
            );
        }

        return dto;
    }
}


