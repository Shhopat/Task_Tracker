package com.example.task_tracker.service;

import com.example.task_tracker.details.PersonDetails;
import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PersonRepositories personRepositories;

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
}
