import { useState } from 'react'
import './App.css'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Login from "./pages/Login.jsx";
import Register from "./pages/Register.jsx";

function App() {
  const [count, setCount] = useState(0)

    return (
        <BrowserRouter>
            <Routes>
                {/* Define your routes here */}
                <Route path="/login" element={<Login/>}/>
                <Route path={"/register"} element={<Register/>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
