import sys, subprocess, tempfile, os
def main():
    code = sys.stdin.read()
    with open("script.py", "w") as f:
        f.write(code)

    try:
        result = subprocess.run(
            ["python3", "script.py"],
            capture_output=True,
            text=True,
            timeout=5
        )
        print("Output:\n", result.stdout)
        if result.stderr:
            print("Errors:\n", result.stderr)
    except subprocess.TimeoutExpired:
        print("Error: Execution timed out")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    main()
