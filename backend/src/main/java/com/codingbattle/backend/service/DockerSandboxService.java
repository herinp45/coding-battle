package com.codingbattle.backend.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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

    public static String[] compareOutputs(String actualOutput, List<String> expectedOutput) {
        String[] actualLines = actualOutput.strip().split("\\R"); // split by any newline
        String[] results = new String[expectedOutput.size()];

        for (int i = 0; i < expectedOutput.size(); i++) {
            String expected = expectedOutput.get(i).trim();
            String actual = i < actualLines.length ? actualLines[i].trim() : "<no output>";

            if (expected.equals(actual)) {
                results[i] = "Test case " + (i + 1) + " passed.";
            } else {
                results[i] = "Test case " + (i + 1) + " failed. Expected: " + expected + ", Got: " + actual;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        DockerSandboxService service = new DockerSandboxService();
        try {
            String testCode = """
                for _ in range(3):
                    n = int(input())
                    print(n + 3)
                """;

            SandboxResult result = service.runCode(testCode, List.of("1", "2", "3"));
            System.out.println("Success: " + result.isSuccess());

            String[] results = compareOutputs(result.getOutput(), List.of("4", "5", "6"));

            for (String line : results) {
                System.out.println(line);
            }

            System.out.println("\nError: " + result.getError());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

