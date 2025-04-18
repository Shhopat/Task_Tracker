package com.example.task_tracker.repositories;

import com.example.task_tracker.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepositories extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);

}
