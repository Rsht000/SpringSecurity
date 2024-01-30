package com.example.springsecurity.Controller;

import com.example.springsecurity.Model.User;
import com.example.springsecurity.Service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
@RestController
@RequestMapping("/api")
public class RestUserController {
    private final UserServiceImpl userServiceImpl;

    public RestUserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/userPager")
    public ResponseEntity<User> userForm(Principal principal) {
        return new ResponseEntity<>(userServiceImpl.findByUserName(principal.getName()), HttpStatus.OK);
    }

}