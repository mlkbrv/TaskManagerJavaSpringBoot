package com.example.task_manager.service;

import com.example.task_manager.entity.Category;
import com.example.task_manager.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long id) {
        return categoryRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

}
