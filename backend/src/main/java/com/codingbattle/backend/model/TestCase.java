package com.codingbattle.backend.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    @Column(name = "input_data", columnDefinition = "TEXT", nullable = false)
    private String inputData;

    @Column(name = "expected_output", columnDefinition = "TEXT", nullable = false)
    private String expectedOutput;

    @Column(name = "is_sample", nullable = false)
    private Boolean isSample = false;

    // ===== Constructors =====
    public TestCase() {}

    public TestCase(String inputData, String expectedOutput, Boolean isSample) {
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
        this.isSample = isSample;
    }

    // ===== Getters & Setters =====
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Problem getProblem() { return problem; }
    public void setProblem(Problem problem) { this.problem = problem; }

    public String getInputData() { return inputData; }
    public void setInputData(String inputData) { this.inputData = inputData; }

    public String getExpectedOutput() { return expectedOutput; }
    public void setExpectedOutput(String expectedOutput) { this.expectedOutput = expectedOutput; }

    public Boolean getSample() { return isSample; }
    public void setSample(Boolean sample) { isSample = sample; }
}
