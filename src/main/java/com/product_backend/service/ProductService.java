package com.product_backend.service;

import com.product_backend.dto.ProductResponseDto;
import com.product_backend.entity.Product;
import com.product_backend.entity.Seller;
import com.product_backend.repository.ProductRepository;
import com.product_backend.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;

    // CREATE
    public ProductResponseDto create(
            String name,
            double price,
            double originalPrice,
            double rating,
            int reviews,
            String category,
            Boolean sale,
            String badge,
            String sellerId,
            MultipartFile image
    ) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Product p = new Product();
        p.setId(UUID.randomUUID().toString());
        p.setName(name);
        p.setPrice(price);
        p.setOriginalPrice(originalPrice);
        p.setRating(rating);
        p.setReviews(reviews);
        p.setCategory(category);
        p.setSale(sale);
        p.setBadge(badge);
        p.setSeller(seller);

        // ---- IMAGE HANDLING ----
        if (image != null && !image.isEmpty()) {
            String imageUrl = "/uploads/" + image.getOriginalFilename();
            p.setImage(imageUrl);
            // (abhi file save optional, baad me disk/cloud pe)
        }

        return map(productRepository.save(p));
    }


    // UPDATE
    public ProductResponseDto update(String id, Product incoming) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(incoming.getName());
        existing.setPrice(incoming.getPrice());
        existing.setOriginalPrice(incoming.getOriginalPrice());
        existing.setRating(incoming.getRating());
        existing.setReviews(incoming.getReviews());
        existing.setImage(incoming.getImage());
        existing.setCategory(incoming.getCategory());
        existing.setSale(incoming.getSale());
        existing.setBadge(incoming.getBadge());

        return map(productRepository.save(existing));
    }

    // LIST
    public Map<String, Object> getProducts(
            String seller,
            String category,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Product> pageData =
                (category == null || category.isBlank())
                        ? productRepository.findBySeller_Handle(seller, pageable)
                        : productRepository.findBySeller_HandleAndCategory(
                        seller, category, pageable
                );

        return Map.of(
                "content", pageData.getContent().stream().map(this::map).toList(),
                "hasMore", pageData.hasNext()
        );
    }

    // MAPPER
    private ProductResponseDto map(Product p) {
        ProductResponseDto dto = new ProductResponseDto();

        dto.set_id(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setOriginalPrice(p.getOriginalPrice());
        dto.setRating(p.getRating());
        dto.setReviews(p.getReviews());
        dto.setImage(p.getImage());
        dto.setCategory(p.getCategory());
        dto.setSale(p.getSale());
        dto.setBadge(p.getBadge());
        dto.setSellerId(p.getSeller().getId());

        return dto;
    }
}
