package com.codingbattle.backend.dto.Problem.TestCaseDTO;

public class TestCaseResponseDTO {
    private String id;
    private String inputData;
    private String expectedOutput;

    /**
     * Constructor for TestCaseResponseDTO
     * @param id
     * @param inputData
     * @param expectedOutput
     */
    public TestCaseResponseDTO(String id, String inputData, String expectedOutput) {
        this.id = id;
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
    }
    public TestCaseResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

}
