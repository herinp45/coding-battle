import { useState } from 'react'
import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";
import PrivateRoute from "./routes/PrivateRoute.jsx";
import Home from "./pages/Home.jsx";
import { AuthProvider } from "./context/AuthContext.jsx";

function App() {
  const [count, setCount] = useState(0)

    return (
        <AuthProvider>
            <BrowserRouter>
                <Routes>
                    {/* Define your routes here */}
                    <Route path={"/login"} element={<Login/>}/>
                    <Route path={"/register"} element={<Register/>}/>
                    <Route path="/dashboard" element={
                        <PrivateRoute>
                            <Home/>
                        </PrivateRoute>
                    }/>
                </Routes>
            </BrowserRouter>
        </AuthProvider>
    );
}

export default App;
