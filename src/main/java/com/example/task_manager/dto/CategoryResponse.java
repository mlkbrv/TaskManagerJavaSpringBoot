package com.example.task_manager.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Long categoryId,
        String name,
        String description
) {
}
