package com.example.task_tracker.controller;

import com.example.task_tracker.model.Person;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getFromLogin() {
        return "login";
    }

    @GetMapping("/registration")
    public String getFromRegistration(@ModelAttribute("person") Person person) {
        return "registration";

    }

    @GetMapping("/save")
    public String registration(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/auth/registration";
        }
        return "redirect:/auth/login";

    }
}
