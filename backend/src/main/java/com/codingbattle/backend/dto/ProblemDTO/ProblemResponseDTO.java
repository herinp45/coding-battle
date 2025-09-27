package com.codingbattle.backend.dto.ProblemDTO;

import com.codingbattle.backend.model.Problem;

public class ProblemResponseDTO {
    private String id;
    private String title;
    private String description;
    private Problem.Difficulty difficulty;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String sampleInput;
    private String sampleOutput;
    private Boolean active;
    private String createdAt;
    private List<TestCaseResponseDTO> testCases;

    public ProblemResponseDTO(String title, String description, Problem.Difficulty difficulty, Integer timeLimit, Integer memoryLimit, String sampleInput, String sampleOutput, Boolean active, String createdAt, List<TestCaseResponseDTO> testCases) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.active = active;
        this.createdAt = createdAt;
        this.testCases = testCases;
    }

    public ProblemResponseDTO(String id, String title, String description, Problem.Difficulty difficulty, Integer timeLimit, Integer memoryLimit, String sampleInput, String sampleOutput, Boolean active, String createdAt, List<TestCaseResponseDTO> testCases) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.active = active;
        this.createdAt = createdAt;
        this.testCases = testCases;
    }

    public ProblemResponseDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Problem.Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Problem.Difficulty difficulty) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<TestCaseResponseDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseResponseDTO> testCases) {
        this.testCases = testCases;
    }
}