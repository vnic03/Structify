import React from 'react';
import useStructure from "../useStructure";
import './stack.css';


const Stack = ({ endpoint }) => {
    const {
        structure, value, setValue, add, remove, clear
    } = useStructure('http://localhost:8080/structures/stack', endpoint);

    return (
        <div className="container">
            <h2>Stack ({endpoint})</h2>
            <div className="bucket">
                {structure.array.filter(item => item !== null).map((item, index) => (
                    <div key={index} className="stack-item">{item.value}</div>
                ))}
            </div>
            <input
                type="text"
                value={value}
                onChange={(e) => setValue(e.target.value)}
                placeholder="Enter a value"
            />
            <button onClick={add} disabled={!value}>push</button>
            <button onClick={remove}>pop</button>
            <button onClick={clear}>clear</button>
        </div>
    );
};

export default Stack;