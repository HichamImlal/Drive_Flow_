package com.example.auth.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
