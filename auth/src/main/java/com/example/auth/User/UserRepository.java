package com.example.auth.User;

import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    public UserEntity findByEmail(String email);
}
