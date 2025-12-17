package com.product_backend.service;

import com.product_backend.entity.Product;
import com.product_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(
            String seller,
            String category,
            Pageable pageable
    ) {
        if (category == null || category.equalsIgnoreCase("All")) {
            return productRepository.findBySellerUsername(seller, pageable);
        }
        return productRepository.findBySellerUsernameAndCategoryName(
                seller, category, pageable
        );
    }
}
