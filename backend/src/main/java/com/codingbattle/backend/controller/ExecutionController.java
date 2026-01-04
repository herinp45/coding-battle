package com.codingbattle.backend.controller;

import com.codingbattle.backend.dto.Execution.ExecutionRequest;
import com.codingbattle.backend.dto.Execution.ExecutionResponseDTO;
import com.codingbattle.backend.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execution")
public class ExecutionController {
    private final ExecutionService executionService;

    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping("/run")
    public ExecutionResponseDTO runCode(@RequestBody ExecutionRequest req) {
        try {
            if (!"python".equals(req.getLanguage())) {
                return new ExecutionResponseDTO("Only Python supported for now", false);
            }

            String output = executionService.runPython(
                    req.getCode(),
                    req.getInput()
            );

            return new ExecutionResponseDTO(output, true);

        } catch (Exception e) {
            return new ExecutionResponseDTO(e.getMessage(), false);
        }
    }
}
