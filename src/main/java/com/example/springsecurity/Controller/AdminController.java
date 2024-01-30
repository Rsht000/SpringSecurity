package com.example.springsecurity.Controller;



import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;

    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String showAdminGeneralPage(Model model, Principal principal) {
        model.addAttribute("login" , userService.findByUserName(principal.getName()));
        return "/admin";
    }
}


