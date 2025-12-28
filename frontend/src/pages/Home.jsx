import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext.jsx";
import axios from "../axios/axios.js";
import Alert from "../components/Alert.jsx";

export default function Home() {
    const [user, setUser] = useState(null);
    const [isMatching, setIsMatching] = useState(false);
    const [alertMessage, setAlertMessage] = useState("");
    const [alertType, setAlertType] = useState("success");
    const [loading, setLoading] = useState(true);

    const { token, logout } = useAuth();
    const navigate = useNavigate();

    useEffect(() => {
        if (!token) {
            navigate("/login");
            return;
        }
        fetchUserData();
    }, [token, navigate]);

    async function fetchUserData() {
        try {
            const response = await axios.get('/auth/me', {
                headers: { 'Authorization': `Bearer ${token}` }
            });
            setUser(response.data);
        } catch (error) {
            setAlertMessage('Failed to load user data');
            setAlertType('error');
            if (error.response?.status === 401) {
                logout();
                navigate("/login");
            }
        } finally {
            setLoading(false);
        }
    }

    // Toggle Join / Cancel Matchmaking
    async function handleJoinMatch() {
        if (isMatching) {
            // Cancel matchmaking
            try {
                await axios.post('/matches/leave', {}, {
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                setAlertMessage("Matchmaking cancelled.");
                setAlertType("error");
                setIsMatching(false);
            } catch (error) {
                setAlertMessage("Error cancelling matchmaking.");
                setAlertType("error");
            }
            return;
        }

        // Join matchmaking
        setIsMatching(true);
        setAlertMessage("");

        try {
            const response = await axios.post('/matches/join', {}, {
                headers: { 'Authorization': `Bearer ${token}` }
            });

            if (!response.data) {
                setAlertMessage("Waiting for an opponent...");
                setAlertType("info");
            } else {
                setAlertMessage("Match found! Redirecting...");
                setAlertType("success");
                navigate(`/match/${response.data.matchId}`);
            }
        } catch (error) {
            setAlertMessage(error.response?.data?.error || "Error joining match.");
            setAlertType("error");
            setIsMatching(false);
        }
    }

    // Auto-leave queue on reload/unmount
    useEffect(() => {
        return () => {
            if (isMatching) {
                axios.post('/matches/leave', {}, {
                    headers: { 'Authorization': `Bearer ${token}` }
                });
            }
        };
    }, [isMatching, token]);

    function handleLogout() {
        logout();
        navigate("/login");
    }

    if (loading) {
        return (
            <div className="min-h-screen bg-black flex items-center justify-center">
                <div className="text-yellow-300 text-2xl">Loading...</div>
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-black text-white relative overflow-hidden">
            {/* Alert */}
            {alertMessage && <Alert type={alertType} message={alertMessage} onClose={() => setAlertMessage("")} />}

            {/* Header */}
            <header className="relative z-10 border-b border-yellow-500/30 bg-black/50 backdrop-blur-sm">
                <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-4 flex justify-between items-center">
                    <h1 className="text-2xl font-bold text-yellow-300">CodeBattle Arena</h1>
                    <div className="flex items-center gap-4">
                        <span className="text-yellow-100">{user?.username || 'User'}</span>
                        <button
                            onClick={handleLogout}
                            className="px-4 py-2 rounded-lg bg-red-600/20 border border-red-500/40 text-red-400 hover:bg-red-600/30 transition text-sm"
                        >
                            Logout
                        </button>
                    </div>
                </div>
            </header>

            {/* Main Content */}
            <main className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
                {/* Welcome Section */}
                <div className="text-center mb-12">
                    <h2 className="text-4xl md:text-5xl font-bold text-yellow-300 mb-4">
                        Welcome, {user?.username || 'Coder'}!
                    </h2>
                    <p className="text-yellow-100 text-lg max-w-2xl mx-auto">
                        Ready to battle? Challenge other coders in real-time problem-solving matches!
                    </p>
                </div>

                {/* Stats Cards */}
                <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-12">
                    <div className="bg-black/50 border border-yellow-500/30 rounded-xl p-6 backdrop-blur-sm">
                        <div className="text-yellow-400 text-sm mb-2">Rating</div>
                        <div className="text-3xl font-bold text-yellow-300">{user?.rating || 1200}</div>
                    </div>
                    <div className="bg-black/50 border border-yellow-500/30 rounded-xl p-6 backdrop-blur-sm">
                        <div className="text-yellow-400 text-sm mb-2">Total Matches</div>
                        <div className="text-3xl font-bold text-yellow-300">{user?.totalGames || 0}</div>
                    </div>
                    <div className="bg-black/50 border border-green-500/30 rounded-xl p-6 backdrop-blur-sm">
                        <div className="text-green-400 text-sm mb-2">Wins</div>
                        <div className="text-3xl font-bold text-green-300">{user?.wins || 0}</div>
                    </div>
                    <div className="bg-black/50 border border-red-500/30 rounded-xl p-6 backdrop-blur-sm">
                        <div className="text-red-400 text-sm mb-2">Losses</div>
                        <div className="text-3xl font-bold text-red-300">{user?.losses || 0}</div>
                    </div>
                </div>

                {/* Join / Cancel Match Section */}
                <div className="bg-black/50 border border-yellow-500/30 rounded-2xl shadow-xl p-8 md:p-12 backdrop-blur-sm max-w-2xl mx-auto mb-12">
                    <h3 className="text-3xl font-semibold mb-4 text-center text-yellow-300">
                        Ready to Battle?
                    </h3>
                    <p className="text-yellow-100 text-center mb-8">
                        Join the matchmaking queue and get paired with an opponent of similar skill level.
                    </p>
                    <button
                        onClick={handleJoinMatch}
                        className="w-full py-4 rounded-lg bg-gradient-to-r from-yellow-400 to-yellow-600 text-black text-xl font-bold hover:opacity-90 transition"
                    >
                        {isMatching ? "Cancel Matchmaking" : "Join Match"}
                    </button>
                </div>
            </main>
        </div>
    );
}
