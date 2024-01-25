package com.example.springsecurity.DbInit;


import com.example.springsecurity.Model.Role;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repositrory.RoleRepo;
import com.example.springsecurity.Repositrory.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.List;

@Component
public class DBinit implements CommandLineRunner {
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;

    public DBinit(UserRepo userRepository, RoleRepo roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        User admin = new User("admin","adminov", 55, "admin", "admin@gmail.com");
        User user = new User("user","userov", 33, "user", "user@yandex.ru");

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        this.roleRepository.save(adminRole);
        this.roleRepository.save(userRole);
        this.userRepository.save(admin);
        this.userRepository.save(user);

        admin.setRoles(new HashSet<>(List.of(adminRole, userRole)));
        user.setRoles(new HashSet<>(List.of(userRole)));

        this.userRepository.save(admin);
        this.userRepository.save(user);


    }
}