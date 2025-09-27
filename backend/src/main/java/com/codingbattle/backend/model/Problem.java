package com.codingbattle.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "problems")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    @Column(name = "time_limit", nullable = false)
    private Integer timeLimit = 5000; // ms

    @Column(name = "memory_limit", nullable = false)
    private Integer memoryLimit = 128; // MB

    @Column(name = "sample_input", columnDefinition = "TEXT")
    private String sampleInput;

    @Column(name = "sample_output", columnDefinition = "TEXT")
    private String sampleOutput;

    @Column(name = "test_cases", columnDefinition = "JSON")
    private String testCases; // store as JSON string

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Default constructor
     */
    public Problem() {
    }

    public Problem(UUID id, String title, String description, Difficulty difficulty, Integer timeLimit, Integer memoryLimit, String sampleInput, String sampleOutput, String testCases, Boolean isActive, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.testCases = testCases;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    // Constructors, getters, and setters
    public Problem(String title, String description, Difficulty difficulty, Integer timeLimit, Integer memoryLimit, String sampleInput, String sampleOutput, String testCases, Boolean isActive, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.testCases = testCases;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
