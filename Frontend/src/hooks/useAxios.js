import { useState, useEffect } from 'react';
import apiClient from '../services/apiRest';



const useAxios = ( url ) =>
{
    let [ response , setResponse ] = useState([]);
    let [ loading, setLoading ] = useState(true);
    let [ error, setError ] = useState('');


    const getData = async () =>
    {
        try
        {
            const { data } = await apiClient.get( url );
            setResponse( data );
            setLoading( false );
        } catch ( error ){
            setError( error.message )
        };
    };

    useEffect( () =>
    {
        getData();

    }, [ url ])

    return { response, loading, error }
}

export default useAxios; 