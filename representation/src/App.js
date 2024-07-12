import React from "react";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from "./components/home/Home";
import Stack from "./components/stack/Stack";
import Queue from "./components/queue/Queue";
import BST from "./components/tree/BST";


function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/stack" element={<Stack endpoint="number" />} />
                <Route path="/queue" element={<Queue endpoint="number" />} />
                <Route path="/bst" element={<BST endpoint="number" />}/>
            </Routes>
        </Router>
    );
}

export default App;
