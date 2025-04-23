package com.example.task_tracker.controller;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.service.PersonDetailService;
import com.example.task_tracker.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/task_tracker")
public class TaskTrackerController {
    private final PersonDetailService personDetailService;
    private final RegistrationService registrationService;

    @Autowired
    public TaskTrackerController(PersonDetailService personDetailService, RegistrationService registrationService) {
        this.personDetailService = personDetailService;
        this.registrationService = registrationService;
    }

    @GetMapping("/welcome")
    public String getHello(Model model, Principal principal) {
        //Principal - имеет один метод который возвращает username текущего пользователя
        Person person = personDetailService.findByUsername(principal.getName());
        model.addAttribute("person", person);
        return "welcome";
    }

    @GetMapping("/user/{id}")
    public String getPeronId(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDetailService.findById(id));
        return "id";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        System.out.println("Контроллер edit");
        model.addAttribute("person", personDetailService.findById(id));
        model.addAttribute("profession", personDetailService.getProfessionsList());
        return "edit";
    }

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, Model model) {
        System.out.println("Контроллер update");
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
            System.out.println("внутри if конт update");
            model.addAttribute("profession", personDetailService.getProfessionsList());
            return "edit";
        }
        System.out.println("после if");
        registrationService.update(id, person);
        return "redirect:/task_tracker/user/" + id;

    }
}
