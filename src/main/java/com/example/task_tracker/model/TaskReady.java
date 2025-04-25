package com.example.task_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_ready")
public class TaskReady {
    public TaskReady(int id, String name, Person person) {
        this.id = id;
        this.name = name;
        this.person = person;
    }

    public TaskReady() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 200, nullable = false)
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
