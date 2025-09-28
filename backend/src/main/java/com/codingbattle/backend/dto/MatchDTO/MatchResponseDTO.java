package com.codingbattle.backend.dto.MatchDTO;

import java.time.Instant;
import java.util.UUID;


public class MatchResponseDTO {
    private UUID id;
    private UUID problemId;
    private UUID user1Id;
    private UUID user2Id;
    private UUID winnerId;
    private Instant startedAt;

    public MatchResponseDTO() {
    }

    public MatchResponseDTO(UUID id, UUID problemId, UUID user1Id, UUID user2Id, UUID winnerId, Instant startedAt) {
        this.id = id;
        this.problemId = problemId;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.winnerId = winnerId;
        this.startedAt = startedAt;
    }

    public MatchResponseDTO(UUID problemId, UUID user1Id, UUID user2Id, UUID winnerId, Instant startedAt) {
        this.problemId = problemId;
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.winnerId = winnerId;
        this.startedAt = startedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
    }

    public UUID getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(UUID user1Id) {
        this.user1Id = user1Id;
    }

    public UUID getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(UUID user2Id) {
        this.user2Id = user2Id;
    }

    public UUID getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(UUID winnerId) {
        this.winnerId = winnerId;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }
}
