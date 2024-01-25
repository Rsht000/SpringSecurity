package com.example.springsecurity.Service;

import com.example.springsecurity.Model.Role;
import com.example.springsecurity.Repositrory.RoleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{
    private final RoleRepo roleRepository;

    public RoleServiceImpl(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }


}
