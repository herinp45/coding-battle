package com.codingbattle.backend.dto.Problem.ProblemDTO;

import com.codingbattle.backend.model.Problem;
import com.codingbattle.backend.dto.Problem.TestCaseDTO.TestCaseRequestDTO;

import java.util.List;

public class ProblemRequestDTO {
    private String title;
    private String description;
    private Problem.Difficulty difficulty;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String sampleInput;
    private String sampleOutput;
    private Boolean active;
    private List<TestCaseRequestDTO> testCases;

    // ===== Constructors =====
    public ProblemRequestDTO() {}

    public ProblemRequestDTO(String title, String description, Problem.Difficulty difficulty,
                             Integer timeLimit, Integer memoryLimit, String sampleInput,
                             String sampleOutput, Boolean active, List<TestCaseRequestDTO> testCases) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.active = active;
        this.testCases = testCases;
    }

    // ===== Getters & Setters =====


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

    public List<TestCaseRequestDTO> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCaseRequestDTO> testCases) {
        this.testCases = testCases;
    }
}
