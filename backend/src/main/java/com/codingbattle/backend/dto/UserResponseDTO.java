package com.codingbattle.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * User Response Data Transfer Object (without password)
 */
public class UserResponseDTO {
    
    private Long id;
    
    @NotBlank(message = "Username is required")
    private String username;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Role is required")
    private String role;
    
    @NotNull(message = "Rating is required")
    @PositiveOrZero(message = "Rating must be positive or zero")
    private Integer rating;

    // Default constructor
    public UserResponseDTO() {
    }

    // Constructor with all fields
    public UserResponseDTO(Long id, String username, String email, String role, Integer rating) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.rating = rating;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", rating=" + rating +
                '}';
    }
}
