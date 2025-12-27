package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.service.Match.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

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
