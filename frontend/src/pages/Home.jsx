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

    const { token, logout,  } = useAuth();
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
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            setUser(response.data);
        }
        catch (error) {
            console.error('Error fetching user data:', error);
            setAlertMessage('Failed to load user data');
            setAlertType('error');

            // If unauthorized, redirect to login
            if (error.response?.status === 401) {
                logout();
                navigate("/login");
            }
        } finally {
            setLoading(false);
        }
    }

    async function handleJoinMatch() {
        setIsMatching(true);
        setAlertMessage("");

        try {
            const response = await axios.post('/match/join', {}, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });

            setAlertMessage("Match found! Redirecting...");
            setAlertType("success");

            setTimeout(() => {
                navigate(`/match/${response.data.matchId}`);
            }, 1000);
        } catch (error) {
            setAlertMessage(error.response?.data?.error || "Error joining match. Please try again.");
            setAlertType("error");
            setIsMatching(false);
        }
    }

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
            {/* Alert Component */}
            {alertMessage && <Alert type={alertType} message={alertMessage} onClose={() => setAlertMessage("")} />}

            {/* Background Decorations */}
            <div className="absolute top-0 left-0 w-full h-full pointer-events-none">
                <div className="absolute -top-32 -left-32 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl"></div>
                <div className="absolute -bottom-32 -right-32 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl"></div>
                <div className="absolute top-1/2 left-1/2 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl"></div>
            </div>

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

                {/* Join Match Section */}
                <div className="bg-black/50 border border-yellow-500/30 rounded-2xl shadow-xl p-8 md:p-12 backdrop-blur-sm max-w-2xl mx-auto mb-12">
                    <h3 className="text-3xl font-semibold mb-4 text-center text-yellow-300">
                        Ready to Battle?
                    </h3>
                    <p className="text-yellow-100 text-center mb-8">
                        Join the matchmaking queue and get paired with an opponent of similar skill level.
                    </p>
                    <button
                        onClick={handleJoinMatch}
                        disabled={isMatching}
                        className="w-full py-4 rounded-lg bg-gradient-to-r from-yellow-400 to-yellow-600 text-black text-xl font-bold hover:opacity-90 transition disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                        {isMatching ? (
                            <span className="flex items-center justify-center gap-3">
                                <svg className="animate-spin h-6 w-6" viewBox="0 0 24 24">
                                    <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4" fill="none"></circle>
                                    <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                                </svg>
                                Finding Opponent...
                            </span>
                        ) : (
                            "Join Match"
                        )}
                    </button>
                </div>

                {/* Quick Links */}
                <div className="flex justify-center">
                    <button
                        onClick={() => navigate('/leaderboard')}
                        className="bg-black/50 border border-yellow-500/30 rounded-xl p-6 backdrop-blur-sm hover:border-yellow-400/50 transition text-center group max-w-md w-full"
                    >
                        <h4 className="text-xl font-semibold text-yellow-300 mb-2 group-hover:text-yellow-400">Leaderboard</h4>
                        <p className="text-yellow-100 text-sm">See top players</p>
                    </button>
                </div>
            </main>
        </div>
    );
}