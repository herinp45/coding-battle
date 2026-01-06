package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.Execution.SubmissionRequestDTO;
import com.codingbattle.backend.dto.Execution.SubmissionResponseDTO;
import com.codingbattle.backend.service.SubmissionService;
import com.codingbattle.backend.service.User.AuthService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final AuthService authService;

    @Autowired
    public SubmissionController(SubmissionService submissionService, AuthService authService) {
        this.submissionService = submissionService;
        this.authService = authService;
    }

    @PostMapping("/submit")
    public ResponseEntity<SubmissionResponseDTO> submitCode(
            @RequestBody SubmissionRequestDTO submissionRequestDTO
            ) {
        UUID userId = authService.getCurrentUser().getId();


        return ResponseEntity.ok(submissionService.submit(
                userId,
                submissionRequestDTO
        ));
    }

}
