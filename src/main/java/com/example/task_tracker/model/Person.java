package com.example.task_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    public Person() {
    }

    public Person(String username, String password, String position) {
        this.username = username;
        this.password = password;
        this.position = position;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = false, length = 40)
    @Size(min = 3, max = 40, message = "username should be between 3 - 40 characters")
    @NotEmpty(message = "username should not be empty")
    private String username;

    @Column(name = "password", nullable = false, unique = false, length = 100)
    @Size(min = 8, message = "password should be min 8")
    @NotEmpty(message = "password should not be empty")
    private String password;

    @Column(name = "role", nullable = false, unique = false, length = 100)
    private String role;

    @Column(name = "position", nullable = false, length = 40)
    @NotEmpty(message = "should not be empty")
    private String position;

    @Column(name = "countTasksReady", nullable = false)
    private int countTasksReady;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "countTasks", nullable = true)
    private int countTasks;

    public int getCountTasks() {
        return taskList.size();
    }

    public void setCountTasks(int countTasks) {
        this.countTasks = countTasks;
    }

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<Task> taskList;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private List<TaskReady> taskReadyList;

    public List<Task> getTaskList() {
        return taskList;
    }

    public List<TaskReady> getTaskReadyList() {
        return taskReadyList;
    }

    public void setTaskReadyList(List<TaskReady> taskReadyList) {
        this.taskReadyList = taskReadyList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCountTasksReady() {
        return taskReadyList.size();
    }

    public void setCountTasksReady(int countTasksReady) {
        this.countTasksReady = countTasksReady;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
