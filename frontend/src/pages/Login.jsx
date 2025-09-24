import { useState } from "react";

export default function Login() {
    const [remember, setRemember] = useState(false);

    return (
        <div className="flex items-center justify-center min-h-screen bg-black text-white relative overflow-hidden px-4
        md:flex-col">


            <div className="grid grid-cols-1 md:grid-cols-2 gap-8 md:gap-10 w-full max-w-6xl z-10">
                {/* Left Section */}
                <div className="flex flex-col justify-center space-y-3 md:space-y-4 text-center md:text-left">
                    <h1 className="text-3xl md:text-5xl font-bold text-yellow-300">Welcome Back</h1>
                    <p className="text-yellow-100 text-base md:text-lg max-w-md mx-auto md:mx-0">
                        We're glad to see you again! Please login to continue exploring the
                        dashboard and managing your account.
                    </p>
                </div>

                {/* Right Section - Login Form */}
                <div
                    className="bg-black/50 border relative border-yellow-500/30 rounded-2xl shadow-xl p-6 md:p-12 backdrop-blur-sm w-full max-w-lg mx-auto">


                    <h2 className="text-2xl md:text-3xl font-semibold mb-6 md:mb-8 text-center text-yellow-300">
                        Login
                    </h2>
                    <form className="space-y-4 md:space-y-6">
                        <input
                            type="text"
                            placeholder="Username"
                            className="w-full px-4 md:px-5 py-2.5 md:py-3 rounded-lg bg-black border border-yellow-500/40 text-white focus:outline-none focus:ring-2 focus:ring-yellow-400 text-sm md:text-base"
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            className="w-full px-4 md:px-5 py-2.5 md:py-3 rounded-lg bg-black border border-yellow-500/40 text-white focus:outline-none focus:ring-2 focus:ring-yellow-400 text-sm md:text-base"
                        />

                        <div className="flex items-center space-x-2 text-sm md:text-base">
                            <input
                                type="checkbox"
                                id="remember"
                                checked={remember}
                                onChange={(e) => setRemember(e.target.checked)}
                                className="accent-yellow-400"
                            />
                            <label htmlFor="remember" className="text-yellow-200">
                                Remember me
                            </label>
                        </div>

                        <button
                            type="submit"
                            className="w-full py-2.5 md:py-3 rounded-lg bg-gradient-to-r from-yellow-400 to-yellow-600 text-black font-bold hover:opacity-90 transition text-sm md:text-base"
                        >
                            Login
                        </button>

                        <p className="text-center text-yellow-200 text-xs md:text-sm">
                            Don’t have an account? {" "}
                            <a href="/signup" className="underline text-yellow-400 hover:text-yellow-300">
                                Sign up here
                            </a>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    );
}
