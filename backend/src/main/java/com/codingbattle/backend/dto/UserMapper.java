package com.codingbattle.backend.dto;

import com.codingbattle.backend.model.User;

/**
 * Mapper utility class for converting between User entity and DTOs
 */
public class UserMapper {

    /**
     * Convert User entity to UserResponseDTO (without password)
     */
    public static UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }
        
        return new UserResponseDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole(),
            user.getRating()
        );
    }

    /**
     * Convert UserRequestDTO to User entity (for creation)
     */
    public static User toEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }
        
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());
        user.setRating(1200); // Default rating for new users
        
        return user;
    }

    /**
     * Update existing User entity with UserRequestDTO
     */
    public static void updateEntity(User existingUser, UserRequestDTO userRequestDTO) {
        if (existingUser == null || userRequestDTO == null) {
            return;
        }
        
        existingUser.setUsername(userRequestDTO.getUsername());
        existingUser.setEmail(userRequestDTO.getEmail());
        
        // Only update password if provided
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().trim().isEmpty()) {
            existingUser.setPassword(userRequestDTO.getPassword());
        }
        
        existingUser.setRole(userRequestDTO.getRole());
    }
}
