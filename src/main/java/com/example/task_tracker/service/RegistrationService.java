package com.example.task_tracker.service;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {
    private final PersonRepositories repositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonRepositories repositories, PasswordEncoder passwordEncoder) {
        this.repositories = repositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        repositories.save(person);

    }
}
