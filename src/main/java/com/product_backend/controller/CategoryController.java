package com.product_backend.controller;

import com.product_backend.entity.Category;
import com.product_backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository repo;

    @PostMapping
    public Category create(@RequestBody Category c) {
        return repo.save(c);
    }

    @GetMapping
    public List<Category> getAll() {
        return repo.findAll();
    }
}

