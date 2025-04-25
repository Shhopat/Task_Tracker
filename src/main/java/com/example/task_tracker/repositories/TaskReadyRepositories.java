package com.example.task_tracker.repositories;

import com.example.task_tracker.model.TaskReady;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskReadyRepositories extends JpaRepository<TaskReady, Integer> {
}
