package com.codingbattle.backend.dto.TestCaseDTO;

public class TestCaseRequestDTO {
    private String inputData;
    private String expectedOutput;
    private Boolean isSample;

    public TestCaseRequestDTO(String inputData, String expectedOutput, Boolean isSample) {
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
        this.isSample = isSample;
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

    public Boolean getSample() {
        return isSample;
    }

    public void setSample(Boolean sample) {
        isSample = sample;
    }
}
