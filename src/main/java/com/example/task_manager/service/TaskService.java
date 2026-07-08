package com.example.task_manager.service;

import com.example.task_manager.dto.TaskRequest;
import com.example.task_manager.dto.TaskResponse;
import com.example.task_manager.entity.Task;
import com.example.task_manager.exception.TaskNotFoundException;
import com.example.task_manager.mapper.TaskMapper;
import com.example.task_manager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    public Page<Task> searchTasksByTitle(String title, Pageable pageable) {
        return taskRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Task> searchTasksByTitleAndCompletion(String title, Boolean completed, Pageable pageable) {
        return taskRepository.findByTitleContainingAndCompleted(title, completed, pageable);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(task);
    }

    public TaskResponse saveTask(TaskRequest task) {
        Task taskToSave = taskMapper.toEntity(task);
        Task savedTask = taskRepository.save(taskToSave);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.updateEntityFromRequest(updatedTask, task);

        return taskMapper.toResponse(taskRepository.save(task));

    }

    public void deleteTaskById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    public List<Task> getCompletedTasks(boolean completed) {
        return taskRepository.findByCompleted(completed);
    }

    public Page<TaskResponse> getCompletedTasks(boolean completed, Pageable pageable) {
        final Page<Task> tasks = taskRepository.findByCompleted(completed, pageable);
        return tasks.map(TaskMapper::toResponse);
    }

    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    public Page<Task> getTasksByCompletion(Boolean completed, Pageable pageable) {
        return taskRepository.findTasksByCompletionStatus(completed, pageable);
    }
}
