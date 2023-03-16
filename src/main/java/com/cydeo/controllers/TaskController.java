package com.cydeo.controllers;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/task")
@Controller
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String create(Model model){

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("listOfProjects", projectService.findAll());
        model.addAttribute("listOfEmployees", userService.findAll());
        model.addAttribute("listOfTasks", taskService.findAll());


        return "task/create";
    }

    @PostMapping("/create")
    public String saveProjectButn(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

}
