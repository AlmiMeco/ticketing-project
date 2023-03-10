package com.cydeo.controllers;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final RoleService role;
    private final UserService user;

    public UserController(RoleService role, UserService user) {
        this.role = role;
        this.user = user;
    }


    @RequestMapping("/create")
    public String create(Model model) {

        model.addAttribute("user", new UserDTO());
        model.addAttribute("listOfRoles", role.findAll() );
        model.addAttribute("listOfUsers", user.findAll());

        return "user/create";
    }


}
