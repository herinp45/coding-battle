package com.codingbattle.backend.dto;

import jakarta.validation.constraints.NotBlank;


/**
 * DTO for login requests using a generic identifier (username or email)
 */
public class LoginRequestDTO {

    @NotBlank(message = "Identifier is required")
    private String identifier; // can be username or email

    @NotBlank(message = "Password is required")
    private String password;

    /**
     * Constructor with all fields
     * @param identifier Username or email
     * @param password Password
     */
    public LoginRequestDTO(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }

    // Getters and setters
    public String getIdentifier() {
        return identifier;
    }
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
