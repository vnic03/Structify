import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
import Home from "./components/home/Home";
import Stack from "./components/stack/Stack";
import Queue from "./components/queue/Queue";


function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/stack" element={<Stack endpoint="number" />} />
                <Route path="/queue" element={<Queue endpoint="number" />} />
            </Routes>
        </Router>
    );
}

export default App;
