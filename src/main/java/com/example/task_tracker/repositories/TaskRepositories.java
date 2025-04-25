package com.example.task_tracker.repositories;

import com.example.task_tracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepositories extends JpaRepository<Task, Integer> {
}
