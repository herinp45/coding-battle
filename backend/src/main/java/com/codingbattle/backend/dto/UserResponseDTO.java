package com.codingbattle.backend.dto;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * User Response Data Transfer Object (safe to return in API responses)
 */
public class UserResponseDTO {

    private UUID id;

    private String username;

    private String email;

    private Integer rating;

    private Integer wins;

    private Integer losses;


    private LocalDateTime createdAt;

    /**
     * Derived field: win rate %
     */
    private Double winRate;

    private Integer totalGames;

    /**
     * Default constructor
     */
    public UserResponseDTO() {}

    /**
     * Parameterized constructor
     * @param username
     * @param email
     * @param rating
     * @param wins
     * @param losses
     * @param createdAt
     * @param winRate
     * @param totalGames
     */
    public UserResponseDTO(String username, String email, Integer rating, Integer wins, Integer losses, LocalDateTime createdAt, Double winRate, Integer totalGames) {
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.wins = wins;
        this.losses = losses;
        this.createdAt = createdAt;
        this.winRate = winRate;
        this.totalGames = totalGames;
    }

    /**
     * Full parameterized constructor
     * @param id
     * @param username
     * @param email
     * @param rating
     * @param wins
     * @param losses
     * @param createdAt
     * @param winRate
     * @param totalGames
     */
    public UserResponseDTO(UUID id, String username, String email, Integer rating, Integer wins, Integer losses, LocalDateTime createdAt, Double winRate, Integer totalGames) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.rating = rating;
        this.wins = wins;
        this.losses = losses;
        this.createdAt = createdAt;
        this.winRate = winRate;
        this.totalGames = totalGames;
    }

    // Getters and Setters

    /**
     * Get ID
     * @return id
     */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getWinRate() {
        return winRate;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }
}
