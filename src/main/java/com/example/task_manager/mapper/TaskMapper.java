package com.example.task_manager.mapper;

import com.example.task_manager.dto.CategoryResponse;
import com.example.task_manager.dto.TaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.entity.Category;
import com.example.task_manager.entity.Task;
import com.example.task_manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final CategoryService categoryService;

    @Autowired
    public TaskMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Task toEntity(TaskRequest taskRequest) {

        Category category = null;

        if (taskRequest!=null && taskRequest.categoryId()!=null) {
            category = categoryService.findById(taskRequest.categoryId());
        }

        return Task.builder()
                .title(taskRequest.title())
                .description(taskRequest.description())
                .completed(taskRequest.completed())
                .category(category)
                .build();
    }

    public static TaskResponse toResponse(Task task) {

        CategoryResponse categoryResponse = null;

        if (task!=null && task.getCategory()!=null) {
            categoryResponse = CategoryResponse.builder()
                    .categoryId(task.getCategory().getId())
                    .name(task.getCategory().getName())
                    .description(task.getCategory().getDescription())
                    .build();
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .category(categoryResponse)
                .build();
    }

    public void updateEntityFromRequest(TaskRequest taskRequest, Task task) {
        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setCompleted(taskRequest.completed());
        if(taskRequest.categoryId()!=null) {
            task.setCategory(categoryService.findById(taskRequest.categoryId()));
        }
    }


}
