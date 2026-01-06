package com.codingbattle.backend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ExecutionService {
    private static final int TIMEOUT_SECONDS = 5;

    public ExecutionService() {
    }

    /**
     * Runs Python code in a Docker container.
     *
     * @param code  the Python code to execute
     * @param input the input to provide to the code
     * @return the output from the code execution
     * @throws Exception if an error occurs during execution
     */
    public String runPython(String code, String input) throws Exception {

        // Create a temporary directory to hold the code file
        Path tempDir = Files.createTempDirectory("sandbox-");
        Path codeFile = tempDir.resolve("main.py");

        // Write the code to a file
        Files.writeString(codeFile, code);

        // Prepare the Docker command
        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm",
                "-i",
                "-v", tempDir.toAbsolutePath() + ":/app",
                "codebattle-python"
        );

        Process process = pb.start();
        // Provide input to the process if available
        if (input != null && !input.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(process.getOutputStream()))) {
                writer.write(input);
                writer.flush();
            }
        }

        // Wait for the process to complete with a timeout
        boolean finished = process.waitFor(TIMEOUT_SECONDS, java.util.concurrent.TimeUnit.SECONDS);

        if (!finished) {
            process.destroyForcibly();
            throw new RuntimeException("Code execution timed out");
        }

        String stdout = new String(process.getInputStream().readAllBytes());
        String stderr = new String(process.getErrorStream().readAllBytes());
        return stderr.isBlank() ? stdout : stderr;

    }
}
