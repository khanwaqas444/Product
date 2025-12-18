package com.product_backend.repository;

import com.product_backend.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<Seller> findByHandle(String handle);
}
