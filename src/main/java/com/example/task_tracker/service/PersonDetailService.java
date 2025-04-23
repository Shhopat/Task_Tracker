package com.example.task_tracker.service;

import com.example.task_tracker.details.PersonDetails;
import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonRepositories personRepositories;
    private final List<String> professionsList = Arrays.asList("backend", "frontend", "tester");

    @Autowired
    public PersonDetailService(PersonRepositories personRepositories) {
        this.personRepositories = personRepositories;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepositories.findByUsername(username);
        if (optionalPerson.isEmpty()) {
            throw new UsernameNotFoundException("not found username: " + username);
        }

        return new PersonDetails(optionalPerson.get());
    }

    public List<String> getProfessionsList() {
        return professionsList;
    }

    @Transactional
    public Person findById(int id) {
        return personRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(int id) {
        personRepositories.deleteById(id);
    }

    @Transactional
    public Person findByUsername(String username) {
        return personRepositories.findByUsername(username).orElse(null);
    }


}
