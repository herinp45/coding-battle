package com.codingbattle.backend.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class DockerSandboxService {

    public static class SandboxResult {
        private final boolean success;
        private final String output;
        private final String error;

        public SandboxResult(boolean success, String output, String error) {
            this.success = success;
            this.output = output;
            this.error = error;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getOutput() {
            return output;
        }

        public String getError() {
            return error;
        }
    }

    public SandboxResult runCode(String code, List<String> input) throws IOException, InterruptedException {

        String inputStr = String.join("\n", input);
        String payload = code + "###INPUT###" + inputStr;

        ProcessBuilder pb = new ProcessBuilder(
                "docker", "run", "--rm", "-i",
                "--memory=128m", "--cpus=0.5",
                "python-test"
        );


        Process process = pb.start();

        // Send input to stdin of container
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(process.getOutputStream()))) {
            writer.write(payload);
            writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Capture stdout
        String stdout = new String(process.getInputStream().readAllBytes());

        // Capture stderr
        String stderr = new String(process.getErrorStream().readAllBytes());

        int exitCode = process.waitFor();


        // For now, just return a dummy success result
        return new SandboxResult(exitCode==0, stdout, stderr);
    }

    public static void main(String[] args) {
        DockerSandboxService service = new DockerSandboxService();
        try {
            SandboxResult result = service.runCode(
                    // Python code
                    "for _ in range(3):\n    print(input())",
                    // Multiple lines of input
                    List.of("Hello", "Sandbox", "World")
            );
            System.out.println("Success: " + result.isSuccess());
            System.out.println("Output: " + result.getOutput());
            System.out.println("Error: " + result.getError());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
