import { useState, useEffect , useContext } from 'react';
import axios from 'axios';


const useAxios = ( url,  method,  payload ) => {
    
    const [ data, setData ] = useState([]);
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(true);
    const contextInstance = useContext(AxiosContext);
    const instance = useMemo(() => {
        return contextInstance || axios; 
    }, [contextInstance ]); 
    const controllerRef = useRef(new AbortController());
    const cancel = () => { 
        controllerRef.current.abort();
    };

    useEffect(() =>{
        (async ()=>{
            try { 
                const response = await instance.request({
                    signal: controllerRef.current.signal,
                    data:payload,
                    method,
                    url,
                });

                setData(response.data);
            }   catch ( error ) {
                setError(error.message);
            }   finally {
                setLoading( true )
            }
        })();
    },[]); 

    return { cancel, data, error, loading }; 

};

export default useAxios;
