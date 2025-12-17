package com.product_backend.controller;

import com.product_backend.dto.ContactDto;
import com.product_backend.dto.SellerDto;
import com.product_backend.entity.Seller;
import com.product_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/{username}")
    public SellerDto getSeller(@PathVariable String username) {
        Seller s = sellerService.getSeller(username);

        SellerDto dto = new SellerDto();
        dto.setName(s.getName());
        dto.setUsername(s.getUsername());
        dto.setBio(s.getBio());
        dto.setLocation(s.getLocation());
        dto.setRating(s.getRating());
        dto.setTotalReviews(s.getTotalReviews());
        dto.setFreeShipping(s.getFreeShipping());
        dto.setProfileImage(s.getProfileImage());

        return dto;
    }

    @GetMapping("/{username}/contact")
    public ContactDto getContact(@PathVariable String username) {
        Seller s = sellerService.getSeller(username);

        ContactDto dto = new ContactDto();
        dto.setPhone(s.getPhone());
        dto.setWhatsapp(s.getWhatsapp());
        dto.setEmail(s.getEmail());
        dto.setAddress(s.getAddress());

        return dto;
    }
}
