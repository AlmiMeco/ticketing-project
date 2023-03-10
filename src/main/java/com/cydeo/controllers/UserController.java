package com.cydeo.controllers;

import com.cydeo.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/create")
    public String create(Model model) {

        model.addAttribute("user", new UserDTO());
//        model.addAttribute("listOfRoles", rolesList);
        return "user/create";
    }


}
