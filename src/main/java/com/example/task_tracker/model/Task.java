package com.example.task_tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task {
    public Task() {
    }

    public Task(int id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 200, nullable = false)
    @NotEmpty(message = "name of task should not be empty")
    @Size(min = 5, max = 200, message = "character should be between 10 - 200")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = true)
    private Person person;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
