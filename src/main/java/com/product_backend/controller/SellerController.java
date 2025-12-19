package com.product_backend.controller;

import com.product_backend.dto.ContactDto;
import com.product_backend.dto.SellerResponseDto;
import com.product_backend.dto.SocialLinkDto;
import com.product_backend.entity.Contact;
import com.product_backend.entity.Seller;
import com.product_backend.entity.SocialLink;
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

    // ================= CREATE SELLER =================
    @PostMapping(consumes = "multipart/form-data")
    public SellerResponseDto create(
            @ModelAttribute Seller seller,
            @RequestParam(required = false) MultipartFile avatar
    ) {
        if (avatar != null && !avatar.isEmpty()) {
            seller.setAvatarUrl("/uploads/" + avatar.getOriginalFilename());
        }
        return map(service.create(seller));
    }

    // ================= UPDATE SELLER =================
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public SellerResponseDto update(
            @PathVariable String id,
            @ModelAttribute Seller seller
    ) {
        return map(service.update(id, seller));
    }

    // ================= UPDATE SOCIAL LINKS =================
    @PutMapping(value = "/{id}/contact", consumes = "multipart/form-data")
    public SellerResponseDto updateContact(
            @PathVariable String id,
            @RequestParam String phone,
            @RequestParam String whatsapp,
            @RequestParam String email
    ) {
        Contact c = new Contact();
        c.setPhone(phone);
        c.setWhatsapp(whatsapp);
        c.setEmail(email);

        return map(service.updateContact(id, c));
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
        dto.setFreeShipping(s.isFreeShipping());
        dto.setVerified(s.isVerified());
        dto.setOnlineStatus(s.isOnlineStatus());

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
