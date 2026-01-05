package com.codingbattle.backend.service;

import com.codingbattle.backend.dto.Execution.SubmissionRequestDTO;
import com.codingbattle.backend.dto.Execution.SubmissionResponseDTO;
import com.codingbattle.backend.model.*;
import com.codingbattle.backend.repository.MatchRepo;
import com.codingbattle.backend.repository.SubmissionRepo;
import com.codingbattle.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.SubmissionPublisher;

@Service
public class SubmissionService {
    private MatchRepo matchRepo;
    private ExecutionService executionService;
    private UserRepo userRepo;
    private SubmissionRepo submissionRepo;

    @Autowired
    public SubmissionService(MatchRepo matchRepo, ExecutionService executionService, UserRepo userRepo, SubmissionRepo submissionRepo) {
        this.matchRepo = matchRepo;
        this.executionService = executionService;
        this.userRepo = userRepo;
        this.submissionRepo = submissionRepo;
    }

    public SubmissionResponseDTO submit(
            UUID userId,
            SubmissionRequestDTO submissionRequestDTO
    ) {
        Match match = matchRepo.findById(submissionRequestDTO.getMatchId()).orElseThrow();
        if (!(match.getUser1().getId().equals(userId) || match.getUser2().getId().equals(userId))) {
            throw new RuntimeException("User not part of the match");
        }
        User user = userRepo.findById(userId).orElseThrow();
        Problem problem = match.getProblem();
        List<TestCase> testCases = problem.getTestCases();

        int passedCount = 0;
        for (TestCase testCase : testCases) {
            String result;

            try {
                result = executionService.runPython(
                        submissionRequestDTO.getCode(),
                        testCase.getInputData());
            }
            catch (Exception e) {
                break;
            }

            if (testCase.getExpectedOutput().trim().equals(result.trim())) {
                passedCount++;
            }
        }

        boolean success = passedCount == testCases.size();

        Submission submission = new Submission(
                user,
                match,
                submissionRequestDTO.getCode(),
                passedCount,
                testCases.size(),
                success
        );
        submissionRepo.save(submission);

        return new SubmissionResponseDTO(
                passedCount,
                testCases.size(),
                success
        );

    }



}
