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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    private Person person;
    private List<String> stringList;
    @Mock
    private PersonRepositories personRepositories;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private PersonDetailService personDetailService;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void init() {
        stringList = Arrays.asList("backend", "frontend", "tester");
        person = new Person("Ибрагим", "12121197", stringList.get(1));
    }

    @Test
    void shouldSavePerson() {
        Mockito.when(passwordEncoder.encode(person.getPassword())).thenReturn("xxx1297");
        registrationService.save(person);

        Assertions.assertEquals("ROLE_USER", person.getRole());
        Assertions.assertEquals("xxx1297", person.getPassword());

        Mockito.verify(passwordEncoder).encode("12121197");
        Mockito.verify(personRepositories).save(person);


    }

    @Test
    void shouldUpdate() {
        Person personNew = new Person("Абдуллах", "12121997", "ROLE_ADMIN");
        Mockito.when(personDetailService.findById(person.getId())).thenReturn(person);

        registrationService.update(person.getId(), personNew);

        Assertions.assertEquals(personNew.getUsername(), person.getUsername());
        Assertions.assertEquals(personNew.getPosition(), person.getPosition());

        Mockito.verify(personDetailService).findById(person.getId());
        Mockito.verify(personRepositories).save(person);


    }


}
