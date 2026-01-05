package com.codingbattle.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Entity
public class Submission {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Match match;

    @Column(columnDefinition = "TEXT")
    private String code;

    private  int passedCount;
    private  int totalCount;
    private boolean success;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Submission() {
    }

    public Submission(User user, Match match, String code, int passedCount, int totalCount, boolean success) {
        this.user = user;
        this.match = match;
        this.code = code;
        this.passedCount = passedCount;
        this.totalCount = totalCount;
        this.success = success;
    }

    public Submission(UUID id, User user, Match match, String code, int passedCount, int totalCount, boolean success) {
        this.id = id;
        this.user = user;
        this.match = match;
        this.code = code;
        this.passedCount = passedCount;
        this.totalCount = totalCount;
        this.success = success;
    }

}
