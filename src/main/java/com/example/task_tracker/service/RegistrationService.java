package com.example.task_tracker.service;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class RegistrationService {
    private final PersonDetailService personDetailService;
    private final PersonRepositories repositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PersonDetailService personDetailService, PersonRepositories repositories, PasswordEncoder passwordEncoder) {
        this.personDetailService = personDetailService;
        this.repositories = repositories;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        person.setDate(LocalDateTime.now());
        repositories.save(person);


    }

    @Transactional
    public void update(int id, Person person) {
        Person person1 = personDetailService.findById(id);
        person1.setUsername(person.getUsername());
        person1.setPosition(person.getPosition());
        if (!passwordEncoder.matches(person.getPassword(), person1.getPassword())) {
            //первым аргументом берет открытый пароль, а вторым берет хэшированный пароль
            //первый аргумент хэшируется согласно алгоритму хеширования второго аргумента
            //таким образом мы узнаем являться ли открытая форма обоих паролей одинаковой
            person1.setPassword(passwordEncoder.encode(person.getPassword()));
        }
        repositories.save(person1);


    }
}

