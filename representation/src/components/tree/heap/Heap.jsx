import React, { useEffect, useState, useMemo } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import Tree from 'react-d3-tree';
import '../tree.css';


const url = 'http://localhost:8080/structures/heap';

// param type: Max- or Min-Heap
const Heap = ({ endpoint }) => {
    const { type } = useParams();
    const [heap, setHeap] = useState({ array: [] });
    const [value, setValue] = useState("");

    const fetchHeap = () => {
        axios.get(`${url}/${type}/${endpoint}`)
            .then(res => setHeap(res.data.structure))
            .catch(err => console.log("Error fetching heap data", err));
    }

    useEffect(() => { fetchHeap(); }, [endpoint]);

    const insert = () => {
        axios.post(`${url}/${type}/${endpoint}`, { value })
            .then(() => {
                fetchHeap();
                setValue("");
            })
            .catch(err => console.error("Error adding to tree", err));
    }

    const remove = () => {
        axios.delete(`${url}/${type}/${endpoint}`, { data: { value} })
            .then(() => { fetchHeap(); })
            .catch(err => console.log("Error removing from heap", err));
    }

    const clear = () => {
        axios.delete(`${url}/${type}/${endpoint}?action=clear`).then(() => { fetchHeap(); })
            .catch(err => console.log("Error clearing heap", err));
    }

    const convertToTree = (array) => {
        const buildTree = (index) => {
            if (index >= array.length || array[index] === null) return null;
            const node = {
                name: array[index],
                children: []
            };
            const leftChild = buildTree(2 * index + 1);
            const rightChild = buildTree(2 * index + 2);

            if (leftChild) node.children.push(leftChild);
            if (rightChild) node.children.push(rightChild);
            return node;

        }
        return buildTree(0);
    }

    const memoizedTree = useMemo(() => {
        return convertToTree(heap.array);
    }, [heap.array]);

    return (
        <div>
            <h2>{type} Heap ({endpoint})</h2>
            <div id="tree-container">
                {memoizedTree && (
                    <Tree
                        data={memoizedTree}
                        orientation="vertical"
                        translate={{x: 250, y: 30}}
                        pathFunc="diagonal"
                        pathClassFunc={() => 'link'}
                        branchNodeClassName="node"
                        rootNodeClassName="root"
                    />
                )}
            </div>
            {heap.array.length > 0 && (
                <div id="array-container">
                    <div>
                        <h3>Heap Array</h3>
                        <div className="heap-array">
                            {heap.array.map((item, index) => (
                                <div key={index} className="heap-item">
                                    {item}
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
            <div id="controls">
                <input
                    type="text"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    placeholder="Enter a value"
                />
                <button onClick={insert} disabled={!value}>Insert</button>
                <button onClick={remove} disabled={!value}>Remove</button>
                <button onClick={clear}>Clear</button>
            </div>
        </div>
    );
}

export default Heap;