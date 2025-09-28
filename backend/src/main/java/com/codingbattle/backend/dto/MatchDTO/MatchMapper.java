package com.codingbattle.backend.dto.MatchDTO;
import com.codingbattle.backend.model.Match;
import com.codingbattle.backend.model.Problem;
import com.codingbattle.backend.model.User;

public class MatchMapper {

    public static Match toEntity(MatchRequestDTO dto, Problem problem, User user1, User user2) {
        return new Match(
                problem,
                user1,
                user2,
                dto.getStartedAt() != null ? dto.getStartedAt() : java.time.Instant.now()
        );
    }

    public static MatchResponseDTO toDTO(Match match) {
        MatchResponseDTO dto = new MatchResponseDTO();
        dto.setId(match.getId());
        dto.setProblemId(match.getProblem().getId());
        dto.setUser1Id(match.getUser1().getId());
        dto.setUser2Id(match.getUser2().getId());
        dto.setWinnerId(match.getWinner() != null ? match.getWinner().getId() : null);
        dto.setStartedAt(match.getStartedAt());
        return dto;
    }
}
