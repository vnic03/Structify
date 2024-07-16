import React, { useEffect, useMemo, useState } from 'react';
import axios from 'axios';
import Tree from 'react-d3-tree';
import './tree.css';


const url = 'http://localhost:8080/structures/bst';


const BST = ({ endpoint }) => {
    const [tree, setTree] = useState(null);
    const [value, setValue] = useState("");

    const fetchTree = () => {
        axios.get(`${url}/${endpoint}`)
            .then(res => {
                setTree(res.data.structure.root);
            })
            .catch(err => console.log("Error fetching tree data", err));
    };

    useEffect(() => { fetchTree(); }, [endpoint]);

    const insertNode = () => {
        axios.post(`${url}/${endpoint}`, { value: parseFloat(value) })
            .then(() => {
                fetchTree();
                setValue("");
            })
            .catch(err => console.error("Error adding to tree", err));
    };

    const removeNode = () => {
        axios.delete(`${url}/${endpoint}`, { data: { value: parseFloat(value) } })
            .then(() => { fetchTree(); })
            .catch(err => console.error("Error removing from tree", err));
    };

    const clearTree = () => {
        axios.delete(`${url}/${endpoint}?action=clear`)
            .then(() => { fetchTree(); })
            .catch(err => console.error("Error clearing tree", err));
    };

    const convertToTree = (node) => {
        if (!node) return null;
        const convertedNode = {
            name: node.data,
            children: []
        };
        if (node.left) convertedNode.children.push(convertToTree(node.left));
        if (node.right) convertedNode.children.push(convertToTree(node.right));
        return convertedNode;
    };

    const memoizedTree = useMemo(() => {
        return convertToTree(tree);
    }, [tree]);

    return (
        <div>
            <h2>Binary Search Tree ({endpoint})</h2>
            <div id="tree-container">
                {memoizedTree && (
                    <Tree
                        data={memoizedTree}
                        orientation="vertical"
                        translate={{ x: 250, y: 30 }}
                        pathFunc="diagonal"
                        pathClassFunc={() => 'link'}
                        branchNodeClassName="node"
                        rootNodeClassName="root"
                    />
                )}
            </div>
            <div id="controls">
                <input
                    type="text"
                    value={value}
                    onChange={(e) => setValue(e.target.value)}
                    placeholder="Enter a value"
                />
                <button onClick={insertNode}>Insert</button>
                <button onClick={removeNode}>Remove</button>
                <button onClick={clearTree}>Clear</button>
            </div>
        </div>
    );
};


export default BST;
