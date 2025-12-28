package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.service.Match.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/matches")

public class MatchController {
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
     * Endpoint for a user to join the match queue.
     * @param userId the UUID of the user joining the queue
     * @return MatchResponseDTO if a match is found, otherwise null
     */
    @PostMapping("/join")
    public ResponseEntity<MatchResponseDTO> joinMatchQueue(
            @RequestParam UUID userId
            ) {
        MatchResponseDTO matchResponseDTO = matchService.joinQueue(userId);
        if (matchResponseDTO != null) {
            return ResponseEntity.ok(matchResponseDTO);
        }
        else {
            return ResponseEntity.ok(null);
        }

    }
}
