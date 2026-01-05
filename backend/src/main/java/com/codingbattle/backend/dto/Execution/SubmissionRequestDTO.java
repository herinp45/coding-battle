package com.codingbattle.backend.dto.Execution;

import org.hibernate.validator.constraints.UUID;

public class SubmissionRequestDTO {
    private UUID matchId;
    private String code;
    private String language;

    public SubmissionRequestDTO() {
    }

    public SubmissionRequestDTO(UUID matchId, String code, String language) {
        this.matchId = matchId;
        this.code = code;
        this.language = language;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
