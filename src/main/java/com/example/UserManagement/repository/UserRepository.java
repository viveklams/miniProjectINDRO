package com.example.UserManagement.repository;

import com.example.UserManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username); // Check if username exists
    boolean existsByEmail(String email); // Check if email exists
    boolean existsByName(String name); // Check if name exists, if necessary
}