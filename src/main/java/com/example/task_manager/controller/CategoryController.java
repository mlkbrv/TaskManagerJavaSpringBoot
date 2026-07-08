package com.example.task_manager.controller;


import com.example.task_manager.entity.Category;
import com.example.task_manager.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Category findById(Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        Category create = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }
}
