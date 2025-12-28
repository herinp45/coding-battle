package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.MatchDTO.MatchResponseDTO;
import com.codingbattle.backend.service.Match.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // Join / toggle matchmaking
    @PostMapping("/join")
    public ResponseEntity<MatchResponseDTO> joinOrLeaveQueue() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        MatchResponseDTO match = matchService.joinQueue(username);

        if (match == null) {
            return ResponseEntity.ok().body(null); // waiting or cancelled
        }
        return ResponseEntity.ok(match); // match found
    }

    // Explicit leave (on page reload/unmount)
    @PostMapping("/leave")
    public ResponseEntity<Void> leaveQueue() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        matchService.leaveQueue(username);
        return ResponseEntity.noContent().build();
    }
}
