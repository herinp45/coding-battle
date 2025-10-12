package com.codingbattle.backend.service;

import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemRequestDTO;
import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemResponseDTO;
import com.codingbattle.backend.dto.Problem.ProblemMapper;
import com.codingbattle.backend.repository.ProblemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ProblemService {

    /**
     * Repository for problem entities.
     */
    private final ProblemRepo problemRepo;

    /**
     * Mapper for converting between Problem entities and DTOs.
     */
    private final ProblemMapper problemMapper;

    /**
     * Constructor for ProblemService.
     *
     * @param problemRepo   the repository for problem entities
     * @param problemMapper the mapper for converting problem entities
     */
    @Autowired
    public ProblemService(ProblemRepo problemRepo, ProblemMapper problemMapper) {
        this.problemRepo = problemRepo;
        this.problemMapper = problemMapper;
    }

    /**
     * Adds a new problem to the repository.
     *
     * @param problemRequestDTO problem request data transfer object
     * @return ProblemResponseDTO representing the created problem
     */
    @Transactional
    public ProblemResponseDTO addProblem(ProblemRequestDTO problemRequestDTO) {
        var problem = problemMapper.toEntity(problemRequestDTO);
        var savedProblem = problemRepo.save(problem);
        return problemMapper.toDTO(savedProblem);
    }

    /**
     * Deletes a problem from the repository by its ID.
     *
     * @param problemId ID of the problem to be deleted
     */
    @Transactional
    public void deleteProblem(UUID problemId) {
        if (!problemRepo.existsById(problemId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found");
        }
        problemRepo.deleteById(problemId);
    }

    /**
     * Retrieves a problem by its ID.
     *
     * @param problemId ID of the problem to be retrieved
     * @return ProblemResponseDTO representing the problem
     */
    public ProblemResponseDTO getProblemById(UUID problemId) {
        var problem = problemRepo.findById(problemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Problem not found"));
        return problemMapper.toDTO(problem);
    }

    /**
     * Retrieves all problems from the repository.
     *
     * @return List of ProblemResponseDTO objects representing all problems
     */
    public List<ProblemResponseDTO> getAllProblems() {
        return problemRepo.findAll()
                .stream()
                .map(problemMapper::toDTO)
                .toList();
    }
}
