package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Specify base path for all endpoints in this controller
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity newUser) {
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            System.out.println("User with this email already exists");
            return null;
        }else
           return userRepository.save(newUser);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {
        UserEntity oldUser = userRepository.findByEmail(user.getEmail());

        if (oldUser != null && user.getPassword().equals(oldUser.getPassword())) {
            return oldUser;
        } else {
            return null;
        }
    }

    @GetMapping("/getAll") // Use @GetMapping for fetching data
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}
