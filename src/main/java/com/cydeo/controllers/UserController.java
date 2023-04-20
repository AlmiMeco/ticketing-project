package com.cydeo.controllers;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.stream.events.StartElement;

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
        model.addAttribute("listOfRoles", roleService.listAllRoles() );
        model.addAttribute("listOfUsers", userService.listAllUsers() );

        return "user/create";
    }

    @PostMapping("/create")
    public String addNewUser(@ModelAttribute("user") UserDTO newlyCreatedUser){

        userService.save(newlyCreatedUser);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String selectUserToUpdate(@PathVariable("username") String username, Model model) {

        model.addAttribute("user", userService.findByUserName(username));
        model.addAttribute("listOfRoles", roleService.listAllRoles());
        model.addAttribute("listOfUsers", userService.listAllUsers());

        return "/user/update";
    }

    @PostMapping("/update")
    public String updateButtonRedirect(@ModelAttribute("user") UserDTO user){

        userService.update(user);

        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String delete(@PathVariable("username") String userName){

        userService.deleteFromUiNotFromDb(userName);

        return "redirect:/user/create";
    }

}
