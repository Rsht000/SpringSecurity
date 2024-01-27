package com.example.springsecurity.Controller;



import com.example.springsecurity.Model.Role;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.RoleServiceImpl;
import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping()
    public String carList(Model model, Principal principal) {
        User userTest = userService.findByUserName(principal.getName());
        model.addAttribute("users", userService.displayAllUsers());
        model.addAttribute("allRoles", roleService.getRoleList());
        model.addAttribute("login", userTest);
        return "admin";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }
    @GetMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles",roleService.getRoleList());
        return "Login";
    }

    @PostMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam String username,
                             @RequestParam String surname,
                             @RequestParam int age,
                             @RequestParam String email,
                             @RequestParam String password,
                             @RequestParam("roles") Set<Role> role,
                             @RequestParam("id") User user) {

        user.setUsername(username);
        user.setSurname(surname);
        user.setAge(age);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(role);
        userService.addNewUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

