import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './queue.css';


const url = 'http://localhost:8080/structures/queue';

const Queue = ({ endpoint }) => {
    const [queue, setQueue] = useState({ array: [] });
    const [value, setValue] = useState("");

    const fetchQueue = () => {
        axios.get(`${url}/${endpoint}`).then(res => {
                setQueue(res.data.structure);
            })
            .catch(err => console.log("Error fetching queue data", err));
    };

    useEffect(() => { fetchQueue(); }, [endpoint]);

    const enqueue = () => {
        axios.post(`${url}/${endpoint}`, { value })
            .then(() => {
                fetchQueue();
                setValue("");
            })
            .catch(err => console.log("Error adding to queue", err));
    };

    const dequeue = () => {
        axios.delete(`${url}/${endpoint}`).then(() => { fetchQueue(); })
            .catch(err => console.log("Error removing from queue", err));
    };

    const clear = () => {
        axios.delete(`${url}/${endpoint}?action=clear`).then(() => { fetchQueue(); })
            .catch(err => console.log("Error clearing queue", err));
    };

    return (
        <div>
            <h2>Queue ({endpoint})</h2>
            <div className="queue-container">
                {queue.array.filter(item => item !== null).map((item, index) => (
                    <div className="queue-item" key={index}>{item.value}</div>
                ))}
            </div>
            <input
                type="text"
                value={value}
                onChange={(e) => setValue(e.target.value)}
                placeholder="Enter a value"
            />
            <button onClick={enqueue}>enqueue</button>
            <button onClick={dequeue}>dequeue</button>
            <button onClick={clear}>clear</button>
        </div>
    );
};

export default Queue;
