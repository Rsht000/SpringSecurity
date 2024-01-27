package com.example.springsecurity.Controller;

import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String userForm(Principal principal, Model model) {
        User user1 = userService.findByUserName(principal.getName());
        model.addAttribute("login", user1);
        return "/user";
    }
}
