package com.codingbattle.backend.dto.UserDTO;

import jakarta.validation.constraints.*;


public class UserRequestDTO {
    @NotBlank(message = "Username can not be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "email cannot be blank")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    /**
     * Default constructor
     */
    public UserRequestDTO() {
    }

    /**
     * Constructor with all fields
     * @param username Username
     * @param email Email
     * @param password Password
     */
    public UserRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Getters and setters
     * @return username
     */
    public @NotBlank(message = "Username can not be blank") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String getUsername() {
        return username;
    }

    /**
     * Set username
     * @param username Username
     */
    public void setUsername(@NotBlank(message = "Username can not be blank") @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters") String username) {
        this.username = username;
    }

    /**
     * Get email
     * @return email
     */
    public @NotBlank(message = "email cannot be blank") @Email String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param email Email
     */
    public void setEmail(@NotBlank(message = "email cannot be blank") @Email String email) {
        this.email = email;
    }

    /**
     * Get password
     * @return password
     */
    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String getPassword() {
        return password;
    }

    /**
     * Set password
     * @param password Password
     */
    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character") String password) {
        this.password = password;
    }
}
