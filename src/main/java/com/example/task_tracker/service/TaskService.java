package com.example.task_tracker.service;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.model.Task;
import com.example.task_tracker.repositories.TaskRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepositories taskRepositories;
    private final PersonDetailService personDetailService;

    @Autowired
    public TaskService(TaskRepositories taskRepositories, PersonDetailService personDetailService) {
        this.taskRepositories = taskRepositories;
        this.personDetailService = personDetailService;
    }


    @Transactional
    public List<Task> findAll() {
        return taskRepositories.findAll();
    }

    @Transactional
    public void save(Task task) {
        taskRepositories.save(task);
    }

    @Transactional
    public void deleteById(int taskId) {
        taskRepositories.deleteById(taskId);
    }
    @Transactional
    public Task findById(int taskId) {
        return taskRepositories.findById(taskId).orElse(null);
    }


    @Transactional
    public void update(int taskId, Task task) {
        Task task1 = findById(taskId);
        task1.setName(task.getName());
        taskRepositories.save(task1);
    }


}
