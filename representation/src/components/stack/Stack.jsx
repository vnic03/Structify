import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './stack.css';


const url = 'http://localhost:8080/structures/stack';


const Stack = ({ endpoint }) => {
    const [stack, setStack] = useState({ array: [] });
    const [value, setValue] = useState("");

    const fetchStack = () => {
        axios.get(`${url}/${endpoint}`).then(res => {
            setStack(res.data.structure);
        })
            .catch(err => console.log("Error fetching stack data", err));
    };

    useEffect(() => { fetchStack(); }, [endpoint]);

    const push = () => {
        axios.post(`${url}/${endpoint}`, { value })
            .then(() => {
                fetchStack();
                setValue("");
            })
            .catch(err => console.log("Error adding to stack", err));
    };

    const pop = () => {
        axios.delete(`${url}/${endpoint}`).then(() => { fetchStack(); })
            .catch(err => console.log("Error removing from stack", err));
    };

    const clear = () => {
        axios.delete(`${url}/${endpoint}?action=clear`).then(() => { fetchStack(); })
            .catch(err => console.log("Error clearing stack", err));
    };

    return (
        <div className="container">
            <h2>Stack ({endpoint})</h2>
            <div className="bucket">
                {stack.array.filter(item => item !== null).map((item, index) => (
                    <div key={index} className="stack-item">{item.value}</div>
                ))}
            </div>
            <input
                type="text"
                value={value}
                onChange={(e) => setValue(e.target.value)}
                placeholder="Enter a value"
            />
            <button onClick={push} disabled={!value}>push</button>
            <button onClick={pop}>pop</button>
            <button onClick={clear}>clear</button>
        </div>
    );
}

export default Stack;