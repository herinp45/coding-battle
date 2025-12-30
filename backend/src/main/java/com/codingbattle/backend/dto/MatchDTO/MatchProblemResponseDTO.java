package com.codingbattle.backend.dto.MatchDTO;

import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemResponseDTO;
import java.util.UUID;

public class MatchProblemResponseDTO {

    private UUID matchId;
    private UUID player1Id;
    private UUID player2Id;
    private ProblemResponseDTO problem;

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public UUID getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(UUID player1Id) {
        this.player1Id = player1Id;
    }

    public UUID getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(UUID player2Id) {
        this.player2Id = player2Id;
    }

    public ProblemResponseDTO getProblem() {
        return problem;
    }

    public void setProblem(ProblemResponseDTO problem) {
        this.problem = problem;
    }
}
