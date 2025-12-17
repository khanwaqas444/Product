package com.product_backend.service;

import com.product_backend.entity.Seller;
import com.product_backend.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;

    public Seller getSeller(String username) {
        return sellerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
    }
}
