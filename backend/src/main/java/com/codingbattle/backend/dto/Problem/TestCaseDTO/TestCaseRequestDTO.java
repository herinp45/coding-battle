package com.codingbattle.backend.dto.Problem.TestCaseDTO;

public class TestCaseRequestDTO {
    private String inputData;
    private String expectedOutput;

    public TestCaseRequestDTO(String inputData, String expectedOutput, Boolean isSample) {
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
    }
    public TestCaseRequestDTO() {
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
