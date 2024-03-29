package com.cydeo.controllers;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
//import com.cydeo.service.ProjectService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        model.addAttribute("listOfProjects", projectService.listAllProjects());
        model.addAttribute("listOfManagers", userService.listAllByRole("manager"));

        return "project/create";
    }

    @PostMapping("/create")
    public String addProjectButton(@ModelAttribute("project") ProjectDTO project, Model model){

        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String deleteProject(@PathVariable("projectCode") String projectCode){

        projectService.delete(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete/{projectCode}")
    public String completeProject(@PathVariable("projectCode") String projectCode){

        projectService.complete(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String updateProject(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project", projectService.getByProjectCode(projectCode));
        model.addAttribute("listOfProjects", projectService.listAllProjects());
        model.addAttribute("listOfManagers", userService.listAllByRole("manager"));

        return "project/update";
    }

    @PostMapping("/update")
    public String updateProjectRedirect(@ModelAttribute("project") ProjectDTO project){

        projectService.update(project);

        return "redirect:/project/create";
    }

    @GetMapping("/project-status")
    public String projectStatus(Model model){

        List<ProjectDTO> projectDTOS = projectService.listAllProjectDetails();

        model.addAttribute("listOfProjects", projectDTOS);


        return "/manager/project-status";
    }


}
