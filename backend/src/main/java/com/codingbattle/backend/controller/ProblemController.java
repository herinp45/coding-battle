package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemRequestDTO;
import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemResponseDTO;
import com.codingbattle.backend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/problems")
@CrossOrigin(origins = "http://localhost:3000")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/{id}")
    public ProblemResponseDTO getProblemById(@PathVariable UUID id) {
        return problemService.getProblemById(id);
    }

    @GetMapping("/all")
    public List<ProblemResponseDTO> getAllProblems() {
        return problemService.getAllProblems();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProblemResponseDTO addProblem(@RequestBody ProblemRequestDTO problemRequestDTO) {
        return problemService.addProblem(problemRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProblem(@PathVariable UUID id) {
        problemService.deleteProblem(id);
    }
}
