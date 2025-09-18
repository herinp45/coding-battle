package com.codingbattle.backend.dto;

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

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .rating(user.getRating())
                .wins(user.getWins())
                .losses(user.getLosses())
                .isOnline(user.getIsOnline())
                .createdAt(user.getCreatedAt())
                .winRate(user.getWinRate())
                .totalGames(user.getTotalGames())
                .build();
    }

    /**
     * Convert UserRequestDTO to User entity (for creation)
     * (Note: password hashing should happen in service layer, not here)
     */
    public User toEntity(UserRequestDTO dto) {
        if (dto == null) return null;

        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .passwordHash(dto.getPassword()) // Hash in service layer!
                .rating(1200)
                .build();
    }

    /**
     * Update existing User entity with UserRequestDTO
     * (Only username/email here — password/role should be handled in service)
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
