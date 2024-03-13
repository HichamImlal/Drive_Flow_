package com.example.auth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @PutMapping("/updateImage/{userId}")
    public ResponseEntity<UserEntity> updateUserImage(@PathVariable int userId, @RequestParam("image") MultipartFile image) {
        try {
            UserEntity updatedUserInfo = userService.updateUserImage(userId, image);
            return ResponseEntity.ok(updatedUserInfo);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/getImage/{userId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer userId) {
        try {
            UserEntity user = userService.getUserById(userId);
            if (user != null && user.getImage() != null) {
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(user.getImage());
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    @GetMapping("/images")
    public ResponseEntity<Map<Integer, List<byte[]>>> getImagesForUsers(@RequestParam List<Integer> userIds) {
        Map<Integer, List<byte[]>> userImagesMap = userService.getImagesForUserIds(userIds);
        return ResponseEntity.ok(userImagesMap);
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }
}
