package com.cydeo.controllers;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final UserService userService;
    private final ProjectService projectService;

    public ProjectController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }


    @GetMapping("/create")
    public String viewProject(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("listOfProjects", projectService.findAll());
        model.addAttribute("listOfManagers", userService.findAll());

        return "project/create";
    }


}
