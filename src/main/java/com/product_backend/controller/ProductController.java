package com.product_backend.controller;

import com.product_backend.dto.ProductResponseDto;
import com.product_backend.entity.Product;
import com.product_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // ================= CREATE (FORM-DATA) =================
    @PostMapping(consumes = "multipart/form-data")
    public ProductResponseDto create(
            @RequestParam String name,
            @RequestParam double price,
            @RequestParam double originalPrice,
            @RequestParam double rating,
            @RequestParam int reviews,
            @RequestParam String category,
            @RequestParam Boolean sale,
            @RequestParam(required = false) String badge,
            @RequestParam String sellerId,
            @RequestPart(required = false) MultipartFile image
    ) {
        return service.create(
                name, price, originalPrice, rating, reviews,
                category, sale, badge, sellerId, image
        );
    }


    // ================= UPDATE (FORM-DATA) =================
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ProductResponseDto update(
            @PathVariable String id,
            @ModelAttribute Product p
    ) {
        return service.update(id, p);
    }

    // ================= LIST =================
    @GetMapping
    public Map<String, Object> list(
            @RequestParam String seller,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        return service.getProducts(seller, category, page, size);
    }
}
