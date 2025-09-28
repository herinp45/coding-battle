package com.codingbattle.backend.dto.MatchDTO;
import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemRequestDTO;
import com.codingbattle.backend.dto.UserDTO.UserRequestDTO;

import java.time.Instant;
import java.util.UUID;

public class MatchRequestDTO {
    private UUID problem;
    private UUID player1;
    private UUID player2;
    private Instant startedAt;

    public MatchRequestDTO() {
    }

    public MatchRequestDTO(UUID problem, UUID player1, UUID player2) {
        this.problem = problem;
        this.player1 = player1;
        this.player2 = player2;
        this.startedAt = Instant.now();
    }

    public UUID getProblem() {
        return problem;
    }

    public void setProblem(UUID problem) {
        this.problem = problem;
    }

    public UUID getPlayer1() {
        return player1;
    }

    public void setPlayer1(UUID player1) {
        this.player1 = player1;
    }

    public UUID getPlayer2() {
        return player2;
    }

    public void setPlayer2(UUID player2) {
        this.player2 = player2;
    }

    public Instant getStartedAt() {
        return startedAt;
    }
    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }
}
