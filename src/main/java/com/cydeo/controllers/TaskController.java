package com.cydeo.controllers;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
//import com.cydeo.service.ProjectService;
//import com.cydeo.service.TaskService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("listOfProjects", projectService.listAllProjects());
        model.addAttribute("listOfEmployees", userService.listAllByRole("employee"));
        model.addAttribute("listOfTasks", taskService.listAllTasks());


        return "task/create";
    }

    @PostMapping("/create")
    public String saveTaskButn(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);

        return "redirect:/task/create";
    }

@GetMapping("/delete/{taskID}")
    public String deleteTaskButn(@PathVariable("taskID") Long taskID){

        taskService.delete(taskID);

        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskID}")
    public String updateTaskRequest(@PathVariable("taskID") Long taskID, Model model){

        model.addAttribute("task", taskService.findById(taskID));
        model.addAttribute("listOfProjects", projectService.listAllProjects());
        model.addAttribute("listOfEmployees", userService.listAllByRole("employees"));
        model.addAttribute("listOfTasks", taskService.listAllTasks());

        return "task/update";
    }


//----------------------------------------------------------------------------------------------------------------------

    /* Both Methods work the exact same (if pathVariable {taskID} is the same in the URL path as it is in the field)
      @PathVariable is not requires :: Spring is smart enough to automatically assign it to the correct */

    @PostMapping("/update/{taskID}")
    public String updateTaskAck(TaskDTO task){

        taskService.update(task);

        return "redirect:/task/create";
    }


//    @PostMapping("/update/{taskID}")
//    public String updateTaskAck(@PathVariable("taskID") Long taskID, TaskDTO task){
//
//        task.setTaskID(taskID);
//
//        // since we do not assign an ID when creating/updating a task we will bring the id via @PathVariable
//        // and assign the old/existing taskID to the new Task
//
//        taskService.update(task);
//
//        return "redirect:/task/create";
//    }



////----------------------------------------------------------------------------------------------------------------------


    @GetMapping("/employee/pending-tasks")
    public String employeePendingTasks(Model model){

        model.addAttribute("tasks", taskService.listAllIncompleteTasks(Status.COMPLETE));

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/archive")
    public String employeeArchive(Model model){

        model.addAttribute("tasks", taskService.listAllCompleteTasks(Status.COMPLETE));

        return "/task/archive";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployeePendingTask(@PathVariable Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks", taskService.listAllIncompleteTasks(Status.COMPLETE));

        return "/task/status-update";
    }

    @PostMapping("/employee/update/{taskID}")
    public String employeeUpdateTask(@ModelAttribute("taskID") Long taskID, TaskDTO task) {

        taskService.update(task);

        return "redirect:/task/employee/pending-tasks";

    }


}
