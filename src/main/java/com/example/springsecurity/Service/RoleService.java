package com.example.springsecurity.Service;


import com.example.springsecurity.Model.Role;

public interface RoleService {
    Role findByName(String name);
}
