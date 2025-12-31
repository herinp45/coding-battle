import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useAuth } from "../context/AuthContext.jsx";
import axios from "../axios/axios.js";
import Alert from "../components/Alert.jsx";

export default function MatchPage() {
    const { matchID } = useParams();
    const { token, logout } = useAuth();

    const [matchData, setMatchData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [alertMessage, setAlertMessage] = useState("");
    const [alertType, setAlertType] = useState("success");

    useEffect(() => {
        if (!token) return;

        const fetchMatch = async () => {
            try {
                const response = await axios.get(`/matches/${matchID}`, {
                    headers: { Authorization: `Bearer ${token}` },
                });
                console.log(response.data);
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

    if (loading) {
        return (
            <div className="min-h-screen bg-black flex items-center justify-center">
                <div className="text-yellow-300 text-2xl">Loading...</div>
            </div>
        );
    }

    if (!matchData) {
        return (
            <div className="min-h-screen bg-black flex items-center justify-center text-red-400">
                No match data found.
            </div>
        );
    }

    const { problem } = matchData;

    return (
        <div className="min-h-screen bg-black text-white p-6">
            {alertMessage && (
                <Alert type={alertType} message={alertMessage} onClose={() => setAlertMessage("")} />
            )}

            <div className="max-w-3xl mx-auto bg-black/50 border border-yellow-500/30 rounded-xl p-8 backdrop-blur-sm">
                {/* Title + Difficulty */}
                <div className="flex justify-between items-center mb-4">
                    <h1 className="text-3xl font-bold text-yellow-300">{problem.title}</h1>
                    <span
                        className={`px-3 py-1 rounded-full text-sm font-semibold ${
                            problem.difficulty === "EASY"
                                ? "bg-green-500/30 text-green-300"
                                : problem.difficulty === "MEDIUM"
                                    ? "bg-yellow-500/30 text-yellow-300"
                                    : "bg-red-500/30 text-red-300"
                        }`}
                    >
            {problem.difficulty}
          </span>
                </div>

                {/* Description */}
                <p className="text-yellow-100 mb-6">{problem.description}</p>

                {/* Sample Input */}
                <div className="mb-4">
                    <h2 className="text-yellow-300 font-semibold mb-1">Sample Input:</h2>
                    <pre className="bg-black/40 p-3 rounded text-yellow-100 whitespace-pre-wrap">
            {problem.sampleInput}
          </pre>
                </div>

                {/* Sample Output */}
                <div>
                    <h2 className="text-yellow-300 font-semibold mb-1">Sample Output:</h2>
                    <pre className="bg-black/40 p-3 rounded text-yellow-100 whitespace-pre-wrap">
            {problem.sampleOutput}
          </pre>
                </div>
            </div>
        </div>
    );
}
