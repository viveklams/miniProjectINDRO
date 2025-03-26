package com.example.UserManagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Ensure username is unique
    private String username; // Add username field

    @Column(unique = true) // Ensure name is unique
    private String name; // Maintain the existing name field

    @Column(unique = true) // Ensure email is unique
    private String email;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; } // Getter for username
    public void setUsername(String username) { this.username = username; } // Setter for username
    public String getName() { return name; } // Getter for name
    public void setName(String name) { this.name = name; } // Setter for name
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}