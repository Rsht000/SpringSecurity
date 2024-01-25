package com.example.springsecurity.Controller;

import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.security.Principal;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user", produces = "text/html; charset=UTF-8")
    public String userUpdateForm(Principal principal, Model model) {
        User user1 = userService.findByUserName(principal.getName());
        model.addAttribute("user", user1);
        return "/user";
    }
}
