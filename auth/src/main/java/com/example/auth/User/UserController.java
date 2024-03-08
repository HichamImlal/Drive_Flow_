package com.example.auth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody UserEntity newUser) {
        return userService.registerUser(newUser);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody UserEntity user) {
        return userService.login(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity updatedUser) {
        try {
            UserEntity updatedUserInfo = userService.updateUser(updatedUser);
            return ResponseEntity.ok(updatedUserInfo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/getAll")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
