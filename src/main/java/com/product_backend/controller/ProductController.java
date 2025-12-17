package com.product_backend.controller;

import com.product_backend.dto.ProductDto;
import com.product_backend.entity.Product;
import com.product_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Map<String, Object> getProducts(
            @RequestParam String seller,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products =
                productService.getProducts(seller, category, pageable);

        List<ProductDto> list = products.getContent().stream().map(p -> {
            ProductDto dto = new ProductDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setPrice(p.getPrice());
            dto.setOriginalPrice(p.getOriginalPrice());
            dto.setDiscountPercent(p.getDiscountPercent());
            dto.setRating(p.getRating());
            dto.setOnSale(p.getOnSale());
            dto.setImage(p.getImageUrl());
            return dto;
        }).toList();

        return Map.of(
                "content", list,
                "hasMore", products.hasNext()
        );
    }
}
