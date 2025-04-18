package com.example.task_tracker.service;


import com.example.task_tracker.model.Person;
import com.example.task_tracker.repositories.PersonRepositories;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @Mock
    private PersonRepositories personRepositories;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    public void shouldSavePerson() {
        Person person = new Person("Ибрагим", "1297", null);
        Mockito.when(passwordEncoder.encode(person.getPassword())).thenReturn("xxx1297");
        registrationService.save(person);


        Assertions.assertEquals("ROLE_USER", person.getRole());
        Assertions.assertEquals("xxx1297", person.getPassword());

        Mockito.verify(passwordEncoder).encode("1297");
        Mockito.verify(personRepositories).save(person);


    }


}
