package com.product_backend.repository;

import com.product_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findBySellerUsername(String username, Pageable pageable);

    Page<Product> findBySellerUsernameAndCategoryName(
            String username,
            String category,
            Pageable pageable
    );
}
