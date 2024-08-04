import {useNavigate} from "react-router-dom";
import React from "react";
import './selector.css'

const HeapSelector = () => {
    const navigate = useNavigate();

    const handleSelect = (type) => {
        navigate(`/heap/${type}`);
    };

    return (
        <div className="HeapSelector-container">
            <button onClick={() => handleSelect('min')}>Min-Heap</button>
            <button onClick={() => handleSelect('max')}>Max-Heap</button>
        </div>
    );
};

export default HeapSelector;