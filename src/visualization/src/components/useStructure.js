import { useEffect, useState } from 'react';
import axios from 'axios';


const useStructure = (url, endpoint) => {
    const [structure, setStructure] = useState({ array: [] });
    const [value, setValue] = useState("");


    const fetchStructure = () => {
        axios.get(`${url}/${endpoint}`)
            .then(res => {
                setStructure(res.data.structure);
            })
            .catch(err => console.log("Error fetching data", err));
    };

    useEffect(() => { fetchStructure(); }, [endpoint]);


    const add = () => {
        axios.post(`${url}/${endpoint}`, { value })
            .then(() => {
                fetchStructure();
                setValue("");
            })
            .catch(err => console.log("Error adding item", err));
    }

    const remove = () => {
        axios.delete(`${url}/${endpoint}`)
            .then(() => { fetchStructure(); })
            .catch(err => console.log("Error removing item", err));
    }

    const clear = () => {
        axios.delete(`${url}/${endpoint}?action=clear`)
            .then(() => { fetchStructure(); })
            .catch(err => console.log("Error clearing items", err));
    }

    return {
        structure,
        value,
        setValue,
        add,
        remove,
        clear
    }
};

export default useStructure;