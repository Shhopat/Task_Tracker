package com.example.task_tracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task_tracker")
public class TaskTrackerController {

    @GetMapping("/welcome")
    public String getHello() {
        return "welcome";
    }
}
