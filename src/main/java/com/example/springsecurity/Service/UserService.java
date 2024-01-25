package com.example.springsecurity.Service;


import com.example.springsecurity.Model.User;

import java.util.List;

public interface UserService {



    public void addNewUser(User user);


    public List<User> displayAllUsers();

    public User getUserById(Long id);


    public void deleteUser(Long id);
}
