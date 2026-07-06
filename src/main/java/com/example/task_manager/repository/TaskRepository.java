package com.example.task_manager.repository;


import com.example.task_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCompleted(Boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT t from Task t where t.completed=:completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") boolean completed);
}
