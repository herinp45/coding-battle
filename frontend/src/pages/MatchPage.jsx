import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAuth } from "../context/AuthContext.jsx";
import axios from "../axios/axios.js";
import Alert from "../components/Alert.jsx";
import CodeEditor from "../components/CodeEditor.jsx";

export default function MatchPage() {
    const { matchID } = useParams();
    const { token, logout } = useAuth();

    const [matchData, setMatchData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [alertMessage, setAlertMessage] = useState("");
    const [alertType, setAlertType] = useState("success");

    const [code, setCode] = useState("# Write your code here\n");
    const [language, setLanguage] = useState("python");
    const [output, setOutput] = useState("");

    useEffect(() => {
        if (!token) return;

        const fetchMatch = async () => {
            try {
                const response = await axios.get(`/matches/${matchID}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                setMatchData(response.data);
            }
            catch (err) {
                setAlertMessage(err.response?.data?.error || "Failed to fetch match");
                setAlertType("error");
                if (err.response?.status === 401) logout();
            }
            finally {
                setLoading(false);
            }
        };

        fetchMatch();
    }, [matchID, token, logout]);

    const handleRunCode = async () => {
        setOutput("Running code...");
        try {
            // API integration goes here
            const runResponse = await axios.post("/execution/run", {
                language,
                code,
                input: matchData.problem.sampleInput,
            }, {
                headers: { Authorization: `Bearer ${token}` },
            });
            console.log(runResponse.data);
            setOutput("Code executed successfully!\n\n" + runResponse.data.output);
        }
        catch (err) {
            setOutput(`Error: ${err.response?.data?.error || "Failed to run code"}`);
        }

    };

    const handleSubmit = async () => {
        setAlertMessage("Submitting solution...");
        setOutput("Submitting solution...");
        setAlertType("success");
        try {
            console.log("Submitting code:", { matchID, code, language });
            const submitResponse = await axios.post(`/submissions/submit`, {
                matchId: matchID,
                code,
                language
            }, {
                headers: { Authorization: `Bearer ${token}` },
            });
            console.log(submitResponse.data);
            setOutput(
                "Submission Result:\n\n" +
                "Total Tests: " + submitResponse.data.total +
                "\nPassed Tests: " + submitResponse.data.passed +
                "\nStatus: " + (submitResponse.data.success ? "Passed" : "Some Failed")
            );

            setAlertMessage("Solution submitted successfully!");
        }
        catch (err) {
            setAlertMessage(err.response?.data?.error || "Failed to submit solution");
            setAlertType("error");
        }
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center min-h-screen bg-black">
                <div className="text-xl text-yellow-400">Loading...</div>
            </div>
        );
    }

    if (!matchData) {
        return (
            <div className="flex justify-center items-center min-h-screen bg-black">
                <div className="text-xl text-gray-400">No match data found.</div>
            </div>
        );
    }

    const { problem } = matchData;

    return (
        <div className="min-h-screen p-6 bg-black">
            {alertMessage && (
                <Alert
                    message={alertMessage}
                    type={alertType}
                    onClose={() => setAlertMessage("")}
                />
            )}

            <div className="max-w-7xl mx-auto grid grid-cols-1 lg:grid-cols-2 gap-6">
                {/* LEFT PANEL – PROBLEM */}
                <div
                    className="rounded-lg shadow-xl p-6 overflow-auto"
                    style={{
                        backgroundColor: "#121212",
                        border: "1px solid #2a2a2a",
                        maxHeight: "calc(100vh - 3rem)",
                    }}
                >
                    <div className="flex justify-between items-start mb-6">
                        <h1 className="text-3xl font-bold text-yellow-400">
                            {problem.title}
                        </h1>
                        <span
                            className={`px-3 py-1 rounded-full text-sm font-semibold ${
                                problem.difficulty === "Easy"
                                    ? "bg-green-900 text-green-300"
                                    : problem.difficulty === "Medium"
                                        ? "bg-yellow-900 text-yellow-300"
                                        : "bg-red-900 text-red-300"
                            }`}
                            style={{ border: "1px solid #2a2a2a" }}
                        >
              {problem.difficulty}
            </span>
                    </div>

                    <div className="mb-6">
                        <h2 className="text-xl font-semibold text-yellow-400 mb-3">
                            Description
                        </h2>
                        <p className="text-gray-200 whitespace-pre-wrap leading-relaxed">
                            {problem.description}
                        </p>
                    </div>

                    <div className="mb-6">
                        <h2 className="text-xl font-semibold text-yellow-400 mb-3">
                            Sample Input
                        </h2>
                        <pre className="p-4 rounded-lg bg-black border border-gray-800">
              <code className="text-gray-200 text-sm">
                {problem.sampleInput}
              </code>
            </pre>
                    </div>

                    <div>
                        <h2 className="text-xl font-semibold text-yellow-400 mb-3">
                            Sample Output
                        </h2>
                        <pre className="p-4 rounded-lg bg-black border border-gray-800">
              <code className="text-gray-200 text-sm">
                {problem.sampleOutput}
              </code>
            </pre>
                    </div>
                </div>

                {/* RIGHT PANEL – EDITOR */}
                <div
                    className="rounded-lg shadow-xl p-6 flex flex-col"
                    style={{
                        backgroundColor: "#121212",
                        border: "1px solid #2a2a2a",
                        maxHeight: "calc(100vh - 3rem)",
                    }}
                >
                    <div className="mb-4 flex justify-between items-center">
                        <select
                            value={language}
                            onChange={(e) => setLanguage(e.target.value)}
                            className="px-4 py-2 rounded-lg bg-black text-gray-200 border border-gray-800 focus:outline-none focus:ring-2 focus:ring-yellow-500"
                        >
                            <option value="python">Python</option>
                            <option value="javascript">JavaScript</option>
                            <option value="java">Java</option>
                            <option value="cpp">C++</option>
                            <option value="c">C</option>
                        </select>

                        <div className="flex gap-2">
                            <button
                                onClick={handleRunCode}
                                className="px-4 py-2 rounded-lg font-medium text-white bg-green-600 border border-green-400 hover:opacity-90"
                            >
                                Run Code
                            </button>
                            <button
                                onClick={handleSubmit}
                                className="px-5 py-2 rounded-lg font-semibold bg-yellow-400 text-black border border-yellow-500 hover:opacity-90"
                            >
                                Submit
                            </button>
                        </div>
                    </div>

                    <div className="flex-1 rounded-lg overflow-hidden mb-4 border border-gray-800 bg-black">
                        <CodeEditor
                            language={language}
                            value={code}
                            onChange={setCode}
                        />
                    </div>

                    <div>
                        <h3 className="text-lg font-semibold text-yellow-400 mb-2">
                            Output
                        </h3>
                        <div className="p-4 rounded-lg bg-black border border-gray-800 min-h-[120px] max-h-[200px] overflow-auto">
              <pre className="text-sm text-gray-200 whitespace-pre-wrap">
                {output || "Console output will appear here..."}
              </pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
