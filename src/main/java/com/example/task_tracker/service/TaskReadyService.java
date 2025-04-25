package com.example.task_tracker.service;

import com.example.task_tracker.model.Task;
import com.example.task_tracker.model.TaskReady;
import com.example.task_tracker.repositories.TaskReadyRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskReadyService {
    private final TaskReadyRepositories taskReadyRepositories;
    private final TaskService taskService;

    @Autowired
    public TaskReadyService(TaskReadyRepositories taskReadyRepositories, TaskService taskService) {
        this.taskReadyRepositories = taskReadyRepositories;
        this.taskService = taskService;
    }

    @Transactional
    public void save(int taskId) {
        Task task = taskService.findById(taskId);
        TaskReady taskReady = new TaskReady();
        taskReady.setPerson(task.getPerson());
        taskReady.setName(task.getName());
        taskReadyRepositories.save(taskReady);
    }

    @Transactional
    public List<TaskReady> findAll() {
        return taskReadyRepositories.findAll();
    }
}
