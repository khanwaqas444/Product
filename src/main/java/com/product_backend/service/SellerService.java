package com.product_backend.service;

import com.product_backend.dto.SellerResponseDto;
import com.product_backend.entity.Seller;
import com.product_backend.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository repository;

    // CREATE
    public Seller create(Seller seller) {
        seller.setId(UUID.randomUUID().toString());

        if (seller.getContact() != null) {
            seller.getContact().setSeller(seller);
        }

        if (seller.getSocialLinks() != null) {
            seller.getSocialLinks()
                    .forEach(link -> link.setSeller(seller));
        }

        return repository.save(seller);
    }

    // UPDATE
    public Seller update(String id, Seller incoming) {
        Seller existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        existing.setName(incoming.getName());
        existing.setHandle(incoming.getHandle());
        existing.setDescription(incoming.getDescription());
        existing.setAvatarUrl(incoming.getAvatarUrl());
        existing.setLocation(incoming.getLocation());
        existing.setOverallRating(incoming.getOverallRating());
        existing.setReviewCount(incoming.getReviewCount());
        existing.setFreeShipping(incoming.getFreeShipping());
        existing.setVerified(incoming.getVerified());
        existing.setOnlineStatus(incoming.getOnlineStatus());

        // Contact
        if (incoming.getContact() != null) {
            incoming.getContact().setSeller(existing);
            existing.setContact(incoming.getContact());
        }

        // Social links
        if (incoming.getSocialLinks() != null) {
            incoming.getSocialLinks()
                    .forEach(link -> link.setSeller(existing));
            existing.setSocialLinks(incoming.getSocialLinks());
        }

        return repository.save(existing);
    }

    public Seller getByHandle(String handle) {
        return repository.findByHandle(handle)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
}
