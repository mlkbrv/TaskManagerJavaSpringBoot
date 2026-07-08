package com.example.task_manager.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskResponse(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDate createdAt
) {
}
