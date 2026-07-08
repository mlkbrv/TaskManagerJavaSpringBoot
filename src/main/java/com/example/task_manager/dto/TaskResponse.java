package com.example.task_manager.dto;

import com.example.task_manager.entity.Category;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDate createdAt,
        CategoryResponse category
) {
}
