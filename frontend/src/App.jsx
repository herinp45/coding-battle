import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
        <Routes>
            {/* Define your routes here */}
            <Route path="/login" element={<Login />} />
        </Routes>
    </BrowserRouter>
  )
}

export default App;
