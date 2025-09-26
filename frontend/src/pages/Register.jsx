import { useState } from "react";
import Alert from "../components/Alert.jsx";
import axios from "../axios/axios.js";
import {Link} from "react-router-dom";


export default function Register() {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [alertMessage, setAlertMessage] = useState("");
    const [alertType, setAlertType] = useState("error");


    async function handleSubmit(event) {
        setAlertMessage("");
        event.preventDefault();

        const formData = {
            username,
            email,
            password,
        };

        console.log("Form submitted:", formData);

        try {
            const response = await axios.post("/auth/register", formData);
            console.log("Registration successful:", response.data);
            setAlertMessage("Registration successful! Please log in.");
            setAlertType("success");

        } catch (error) {
            console.error("Registration error:", error);
            if (error.response && error.response.data && error.response.data.error) {
                setAlertMessage(error.response.data.error);
            } else {
                setAlertMessage("An error occurred during registration. Please try again.");
            }
            setAlertType("error");
        }
    }

    return (
        <div className="flex items-center justify-center min-h-screen bg-black text-white relative overflow-hidden px-4 md:flex-col">
            {/* Alert Component */}
            {alertMessage && <Alert type={alertType} message={alertMessage} onClose={() => setAlertMessage("")} />}

            {/* Background Decorations */}
            <div className="absolute top-0 left-0 w-full h-full pointer-events-none">
                <div className="absolute -top-32 -left-32 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl animate-blob"></div>
                <div className="absolute -bottom-32 -right-32 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl animate-blob animation-delay-2000"></div>
                <div className="absolute top-1/2 left-1/2 w-64 h-64 bg-yellow-400 rounded-full opacity-20 filter blur-3xl animate-blob animation-delay-4000"></div>
            </div>

            {/* Main Content */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-8 md:gap-10 w-full max-w-6xl z-10">
                {/* Left Section */}
                <div className="flex flex-col justify-center space-y-3 md:space-y-4 text-center md:text-left">
                    <h1 className="text-3xl md:text-5xl font-bold text-yellow-300">Welcome Back</h1>
                    <p className="text-yellow-100 text-base md:text-lg max-w-md mx-auto md:mx-0">
                        We're glad to see you! Please Register to explore the
                        dashboard and managing your account.
                    </p>
                </div>

                {/* Right Section - Registration Form */}
                <div className="bg-black/50 border relative border-yellow-500/30 rounded-2xl shadow-xl p-6 md:p-12 backdrop-blur-sm w-full max-w-lg mx-auto">
                    <h2 className="text-2xl md:text-3xl font-semibold mb-6 md:mb-8 text-center text-yellow-300">
                        Register
                    </h2>
                    <form className="space-y-4 md:space-y-6" onSubmit={handleSubmit}>
                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            className="w-full px-4 md:px-5 py-2.5 md:py-3 rounded-lg bg-black border border-yellow-500/40 text-white focus:outline-none focus:ring-2 focus:ring-yellow-400 text-sm md:text-base"
                        />
                        <input
                            type="email"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            className="w-full px-4 md:px-5 py-2.5 md:py-3 rounded-lg bg-black border border-yellow-500/40 text-white focus:outline-none focus:ring-2 focus:ring-yellow-400 text-sm md:text-base"
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className="w-full px-4 md:px-5 py-2.5 md:py-3 rounded-lg bg-black border border-yellow-500/40 text-white focus:outline-none focus:ring-2 focus:ring-yellow-400 text-sm md:text-base"
                        />

                        <button
                            type="submit"
                            className="w-full py-2.5 md:py-3 rounded-lg bg-gradient-to-r from-yellow-400 to-yellow-600 text-black font-bold hover:opacity-90 transition text-sm md:text-base"
                        >
                            Register
                        </button>

                        <p className="text-center text-yellow-200 text-xs md:text-sm">
                            Already have an account?{" "}
                            <Link to="/login" className="underline text-yellow-400 hover:text-yellow-300">
                                Login here
                            </Link>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    );
}
