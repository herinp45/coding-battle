package com.codingbattle.backend.dto.Problem;

import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemRequestDTO;
import com.codingbattle.backend.dto.Problem.ProblemDTO.ProblemResponseDTO;
import com.codingbattle.backend.dto.Problem.TestCaseDTO.TestCaseRequestDTO;
import com.codingbattle.backend.dto.Problem.TestCaseDTO.TestCaseResponseDTO;
import com.codingbattle.backend.model.Problem;
import com.codingbattle.backend.model.TestCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProblemMapper {

    public Problem toEntity(ProblemRequestDTO dto) {
        Problem problem = new Problem(
                dto.getTitle(),
                dto.getDescription(),
                dto.getDifficulty(),
                dto.getTimeLimit(),
                dto.getMemoryLimit(),
                dto.getSampleInput(),
                dto.getSampleOutput(),
                dto.getActive()
        );

        if (dto.getTestCases() != null) {
            dto.getTestCases().forEach(tcDto -> {
                TestCase testCase = toEntity(tcDto);
                problem.addTestCase(testCase);
            });
        }

        return problem;
    }

    public ProblemResponseDTO toDTO(Problem problem) {
        ProblemResponseDTO dto = new ProblemResponseDTO();
        dto.setId(problem.getId().toString());
        dto.setTitle(problem.getTitle());
        dto.setDescription(problem.getDescription());
        dto.setDifficulty(problem.getDifficulty());
        dto.setTimeLimit(problem.getTimeLimit());
        dto.setMemoryLimit(problem.getMemoryLimit());
        dto.setSampleInput(problem.getSampleInput());
        dto.setSampleOutput(problem.getSampleOutput());
        dto.setActive(problem.getActive());
        dto.setCreatedAt(problem.getCreatedAt().toString());

        List<TestCaseResponseDTO> testCaseDTOs = problem.getTestCases() == null
                ? List.of()
                : problem.getTestCases().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        dto.setTestCases(testCaseDTOs);

        return dto;
    }

    public TestCase toEntity(TestCaseRequestDTO dto) {
        return new TestCase(
                dto.getInputData(),
                dto.getExpectedOutput(),
                dto.getSample()
        );
    }

    public TestCaseResponseDTO toDTO(TestCase testCase) {
        TestCaseResponseDTO dto = new TestCaseResponseDTO();
        dto.setId(testCase.getId().toString());
        dto.setInputData(testCase.getInputData());
        dto.setExpectedOutput(testCase.getExpectedOutput());
        dto.setSample(testCase.getSample());
        return dto;
    }
}
