package com.codingbattle.backend.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * User Response Data Transfer Object (safe to return in API responses)
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserResponseDTO {

    private UUID id;

    private String username;

    private String email;

    private Integer rating;

    private Integer wins;

    private Integer losses;

    private Boolean isOnline;

    private LocalDateTime createdAt;

    /**
     * Derived field: win rate %
     */
    private Double winRate;

    private Integer totalGames;
}
