package com.codingbattle.backend.dto.UserDTO;

import com.codingbattle.backend.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper utility class for converting between User entity and DTOs
 */
@Component
public class UserMapper {

    public UserMapper() {
    }

    /**
     * Convert User entity to UserResponseDTO (without password)
     */
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) return null;

        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRating(),
                user.getWins(),
                user.getLosses(),
                user.getCreatedAt(),
                user.getWinRate(),
                user.getWins() + user.getLosses()
        );

    }

    /**
     * Convert UserRequestDTO to User entity (for creation)
     * (Note: password hashing should happen in service layer, not here)
     */
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        return new User(dto.getUsername(), dto.getEmail(), dto.getPassword());

    }

    /**
     * Update existing User entity with UserRequestDTO
     * (Only username/email here)
     */
    public void updateEntity(User user, UserRequestDTO dto) {
        if (user == null || dto == null) return;

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPasswordHash(dto.getPassword()); // Hash in service layer!
        }
    }
}
