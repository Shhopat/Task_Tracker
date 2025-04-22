package com.example.task_tracker.controller;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.service.PersonDetailService;
import com.example.task_tracker.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonDetailService personDetailService;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(PersonDetailService personDetailService, RegistrationService registrationService) {
        this.personDetailService = personDetailService;
        this.registrationService = registrationService;
    }


    @GetMapping("/login")
    public String getFromLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String getFromRegistration(@ModelAttribute("person") Person person, Model model) {
        System.out.println("контроллер registration");
        model.addAttribute("profession", personDetailService.getProfessionsList());
        return "registration";

    }

    @PostMapping("/save")
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, Model model) {
        System.out.println("Контроль save");
        if (bindingResult.hasErrors()) {
            model.addAttribute("profession", personDetailService.getProfessionsList());
            return "registration";
        }
        registrationService.save(person);

        return "redirect:/auth/login?registered";

    }
}
