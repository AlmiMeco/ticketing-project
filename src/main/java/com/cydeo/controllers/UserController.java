package com.cydeo.controllers;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService roleService;
    private final UserService userService;

    public UserController(RoleService role, UserService user) {
        this.roleService = role;
        this.userService = user;
    }


    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("listOfRoles", roleService.findAll() );
        model.addAttribute("listOfUsers", userService.findAll());

        return "user/create";
    }

    @PostMapping("/create")
    public String addNewUser(@ModelAttribute("user") UserDTO newlyCreatedUser, Model model){

        model.addAttribute("user", new UserDTO()); // empty object for form (after user is created)
        model.addAttribute("listOfRoles", roleService.findAll() );

        userService.save(newlyCreatedUser);

        model.addAttribute("listOfUsers", userService.findAll());


        return "user/create";
    }


}
