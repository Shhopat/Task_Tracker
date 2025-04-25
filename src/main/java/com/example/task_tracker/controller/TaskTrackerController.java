package com.example.task_tracker.controller;

import com.example.task_tracker.model.Person;
import com.example.task_tracker.model.Task;
import com.example.task_tracker.model.TaskReady;
import com.example.task_tracker.service.PersonDetailService;
import com.example.task_tracker.service.RegistrationService;
import com.example.task_tracker.service.TaskReadyService;
import com.example.task_tracker.service.TaskService;
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
    private final TaskService taskService;
    private final TaskReadyService taskReadyService;

    @Autowired
    public TaskTrackerController(PersonDetailService personDetailService, RegistrationService registrationService, TaskService taskService, TaskReadyService taskReadyService) {
        this.personDetailService = personDetailService;
        this.registrationService = registrationService;
        this.taskService = taskService;
        this.taskReadyService = taskReadyService;
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

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        System.out.println("контроллер users");
        model.addAttribute("users", personDetailService.findAll());
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") int id) {
        personDetailService.deleteById(id);
        return "redirect:/task_tracker/users";
    }

    @GetMapping("/admin")
    public String getUsersForTask(Model model) {
        System.out.println("конт admin");
        model.addAttribute("users", personDetailService.findAll());
        return "admin";
    }

    @GetMapping("/user/{id}/task")
    public String getFormTask(@PathVariable("id") int id, Model model) {
        System.out.println("контроллер getFormTask");

        model.addAttribute("task", new Task());
        model.addAttribute("personId", id);
        return "task";
    }

    @PostMapping("/user/{id}/add/task")
    public String addTask(@ModelAttribute("task") @Valid Task task, BindingResult bindingResult, @PathVariable("id") int personId, Model model) {
        System.out.println("внутри контроллера addTask. task.id:" + task.getId());
        if (bindingResult.hasErrors()) {
            model.addAttribute("personId", personId);

            bindingResult.getAllErrors().forEach(System.out::println);
            System.out.println("внутри контроллера addTask внутри if");
            return "task";

        }
        System.out.println("внутри контроллера addTask после if");
        task.setPerson(personDetailService.findById(personId));

        task.setId(0);


        taskService.save(task);
        return "redirect:/task_tracker/admin";

    }

    @GetMapping("/task/{taskId}/user/{userId}")
    public String taskIsReady(@PathVariable("taskId") int taskId, @PathVariable("userId") int userId) {
        taskReadyService.save(taskId);
        taskService.deleteById(taskId);
        return "redirect:/task_tracker/user/" + userId;

    }

    @GetMapping("/user/{id}/tasks/ready")
    public String getTasksReady(@PathVariable("id") int id, Model model, Principal principal) {
        model.addAttribute("admin", personDetailService.findByUsername(principal.getName()));
        model.addAttribute("tasks", taskReadyService.findAll());
        model.addAttribute("person", personDetailService.findById(id));
        return "tasks";

    }

    @GetMapping("user/{userId}/task/{taskId}")
    public String getFormEditTask(@PathVariable("taskId") int taskId, @PathVariable("userId") int userId, Model model) {
        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("person", personDetailService.findById(userId));
        return "editTask";
    }

    @PatchMapping("/user/{userId}/task/{taskId}")
    public String editTask(@PathVariable("taskId") int taskId, @ModelAttribute("task") @Valid Task task,
                           BindingResult bindingResult, @PathVariable("userId") int userId, Model model) {
        System.out.println("Контроллер editTask");
        if (bindingResult.hasErrors()) {
            model.addAttribute("person", personDetailService.findById(userId));
            return "editTask";

        }
        System.out.println("Контроллер editTask после if taskId:" + taskId);
        taskService.update(taskId, task);
        return "redirect:/task_tracker/user/" + userId;

    }

    @PutMapping("/user/{userId}/task/{taskId}")
    public String deleteTaskById(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) {
        taskService.deleteById(taskId);
        return "redirect:/task_tracker/user/" + userId;
    }
}
