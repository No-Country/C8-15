import { useState , useEfect, useDebugValue } from 'react';
import apiCliente from '../services/api.service';


const useAxios = ( url ) => {

    let [ dataDB, setDataDb ] = useState([]);
    let [ loading , setLoading ] = useState( true );
    let [ error , setError ] = useState('');


    const getData = async () =>{
      try {
          const { data } = await apiCliente.get(url);
          setDataDb( data );
          setLoading( false );
      } catch ( error ) {
          setError( error.message )
      }
    }

    useEfect( () => {
        getData();

    }, [url ])

    return { dataDB, loading , error }
}

export default useAxios; 