package com.codingbattle.backend.dto.TestCaseDTO;

public class TestCaseResponseDTO {
    private String id;
    private String inputData;
    private String expectedOutput;
    private Boolean isSample;

    public TestCaseResponseDTO(String id, String inputData, String expectedOutput, Boolean isSample) {
        this.id = id;
        this.inputData = inputData;
        this.expectedOutput = expectedOutput;
        this.isSample = isSample;
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

    public Boolean getSample() {
        return isSample;
    }

    public void setSample(Boolean sample) {
        isSample = sample;
    }
}
