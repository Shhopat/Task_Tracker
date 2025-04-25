package com.example.task_tracker.service;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonDetailServiceTest {
    @Mock
    private PersonRepositories personRepositories;
    @InjectMocks
    private PersonDetailService personDetailService;

    private List<String> stringList;
    private Person person;


    @BeforeEach
    void set() {
        stringList = Arrays.asList("backend", "frontend", "tester");
        person = new Person("Ibragim", "12121997", stringList.get(0));
        person.setId(1);

    }


    @Test
    void shouldPersonById() {
        Mockito.when(personRepositories.findById(person.getId())).thenReturn(Optional.of(person));

        Person person1 = personDetailService.findById(person.getId());

        Assertions.assertEquals(person.getUsername(), person1.getUsername());
        Assertions.assertEquals(person.getPassword(), person1.getPassword());

        Mockito.verify(personRepositories).findById(person.getId());

    }

    @Test
    void shouldDeleteById() {
        personDetailService.deleteById(person.getId());
        Mockito.verify(personRepositories).deleteById(person.getId());
    }

    @Test
    void shouldGetByUsername() {
        Mockito.when(personRepositories.findByUsername(person.getUsername())).thenReturn(Optional.of(person));
        Person person1 = personDetailService.findByUsername(person.getUsername());

        Assertions.assertEquals(person.getUsername(), person1.getUsername());
        Assertions.assertEquals(person.getPassword(), person1.getPassword());

        Mockito.verify(personRepositories).findByUsername(person.getUsername());

    }


}
