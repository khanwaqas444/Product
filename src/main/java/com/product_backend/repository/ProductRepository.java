package com.product_backend.repository;

import com.product_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findBySeller_Handle(
            String handle,
            Pageable pageable
    );

    Page<Product> findBySeller_HandleAndCategory(
            String handle,
            String category,
            Pageable pageable
    );
}


