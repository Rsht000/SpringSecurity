package com.example.springsecurity.Controller;



import com.example.springsecurity.Model.Role;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.RoleServiceImpl;
import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;


@Controller
public class AdminController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping(value = "/admin", produces = "text/html; charset=UTF-8")
    public String carList(Model model) {
        List<User> users = userService.displayAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping(value = "/addNewUser", produces = "text/html; charset=UTF-8")
    public String newCar(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles",  roleService.getRoleList());
        return "/addNewUser";
    }

    @PostMapping(value = "/addNewUser", produces = "text/html; charset=UTF-8")
    public String create(User user) {
        userService.addNewUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/Update-User", produces = "text/html; charset=UTF-8")
    public String userUpdate(@RequestParam("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getRoleList());
        return "/Update-User";
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

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:admin";

    }
}
