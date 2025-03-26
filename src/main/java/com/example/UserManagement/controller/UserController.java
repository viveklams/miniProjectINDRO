package com.example.UserManagement.controller;

import com.example.UserManagement.exceptions.ResourceAlreadyExistsException;
import com.example.UserManagement.exceptions.ResourceNotFoundException;
import com.example.UserManagement.model.User;
import com.example.UserManagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    // Create a new user
    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws ResourceAlreadyExistsException {
        // Check for existing username and email
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ResourceAlreadyExistsException("Username already exists: " + user.getUsername());
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResourceAlreadyExistsException("Email already exists: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    // Update an existing user
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User newUser)
            throws ResourceNotFoundException, ResourceAlreadyExistsException {
        // Find the existing user by ID
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        // Check if the username is being updated and if it's already taken
        if (newUser.getUsername() != null && !newUser.getUsername().equals(existingUser.getUsername())) {
            if (userRepository.existsByUsername(newUser.getUsername())) {
                throw new ResourceAlreadyExistsException("Username already exists: " + newUser.getUsername());
            }
            existingUser.setUsername(newUser.getUsername());
        }

        // Check if the email is being updated and if it's already taken
        if (newUser.getEmail() != null && !newUser.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(newUser.getEmail())) {
                throw new ResourceAlreadyExistsException("Email already exists: " + newUser.getEmail());
            }
            existingUser.setEmail(newUser.getEmail());
        }

        // Update name if provided
        if (newUser.getName() != null && !newUser.getName().equals(existingUser.getName())) {
            existingUser.setName(newUser.getName());
        }

        // Save and return the updated user
        return userRepository.save(existingUser);
    }

    // Delete a user
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
}