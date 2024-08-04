import React from 'react';
import useStructure from "../useStructure";
import './queue.css';


const Queue = ({ endpoint }) => {
    const {
        structure, value, setValue, add, remove, clear

    } = useStructure('http://localhost:8080/structures/queue', endpoint);

    return (
        <div>
            <h2>Queue ({endpoint})</h2>
            <div className="queue-container">
                {structure.array.filter(item => item !== null).map((item, index) => (
                    <div className="queue-item" key={index}>{item.value}</div>
                ))}
            </div>
            <input
                type="text"
                value={value}
                onChange={(e) => setValue(e.target.value)}
                placeholder="Enter a value"
            />
            <button onClick={add} disabled={!value}>enqueue</button>
            <button onClick={remove}>dequeue</button>
            <button onClick={clear}>clear</button>
        </div>
    );
};

export default Queue;
