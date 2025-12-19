package com.product_backend.service;

import com.product_backend.entity.Contact;
import com.product_backend.entity.Seller;
import com.product_backend.entity.SocialLink;
import com.product_backend.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository repository;

    // ================= CREATE =================
    public Seller create(Seller seller) {
        seller.setId(UUID.randomUUID().toString());

        if (seller.getContact() != null) {
            seller.getContact().setSeller(seller);
        }

        if (seller.getSocialLinks() != null) {
            seller.getSocialLinks().forEach(l -> l.setSeller(seller));
        }

        return repository.save(seller);
    }

    // ================= BASIC UPDATE =================
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
        existing.setFreeShipping(incoming.isFreeShipping());
        existing.setVerified(incoming.isVerified());
        existing.setOnlineStatus(incoming.isOnlineStatus());

        return repository.save(existing);
    }

    // ================= CONTACT UPDATE =================
    public Seller updateContact(String sellerId, Contact incoming) {
        Seller seller = repository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        if (seller.getContact() == null) {
            incoming.setSeller(seller);
            seller.setContact(incoming);
        } else {
            Contact existing = seller.getContact();
            existing.setPhone(incoming.getPhone());
            existing.setWhatsapp(incoming.getWhatsapp());
            existing.setEmail(incoming.getEmail());
        }

        return repository.save(seller);
    }

    // ================= SOCIAL LINKS UPDATE =================
    public Seller updateSocialLinks(String sellerId, List<SocialLink> links) {
        Seller seller = repository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        links.forEach(l -> l.setSeller(seller));
        seller.setSocialLinks(links);

        return repository.save(seller);
    }

    // ================= GET =================
    public Seller getByHandle(String handle) {
        return repository.findByHandle(handle)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
}
