package com.codingbattle.backend.dto.Execution;

public class SubmissionResponseDTO {
    private int passed;
    private int total;
    private boolean success;

    public SubmissionResponseDTO() {
    }

    public SubmissionResponseDTO(int passed, int total, boolean success) {
        this.passed = passed;
        this.total = total;
        this.success = success;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
