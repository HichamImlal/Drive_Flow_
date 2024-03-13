package com.example.auth.User;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity newUser) {
        if (userRepository.findByEmail(newUser.getEmail()) != null) {
            System.out.println("User with this email already exists");
            return null;
        } else {
            return userRepository.save(newUser);
        }
    }

    public UserEntity login(UserEntity user) {
        UserEntity oldUser = userRepository.findByEmail(user.getEmail());
        if (oldUser != null && user.getPassword().equals(oldUser.getPassword())) {
            return oldUser;
        } else {
            return null;
        }
    }
    @Transactional
    public UserEntity updateUser(UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        return userRepository.save(existingUser);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    public UserEntity updateUserImage(int userId, MultipartFile image) throws IOException {
        UserEntity existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setImage(image.getBytes());
        return userRepository.save(existingUser);
    }
    public UserEntity getUserById(int userId) {
        Optional<UserEntity> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null);
    }

    public Map<Integer, List<byte[]>> getImagesForUserIds(List<Integer> userIds) {
        Map<Integer, List<byte[]>> userImagesMap = new HashMap<>();

        for (Integer userId : userIds) {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            userOptional.ifPresent(user -> {
                byte[] image = user.getImage();
                userImagesMap.put(userId, Collections.singletonList(image));
            });
        }
        return userImagesMap;
    }
}
