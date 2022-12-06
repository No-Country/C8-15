import { createContext , useState  } from "react";

export const AuthContext = createContext();

export const AuthProvider = ({ children}) =>{
  const [ login , setLogin ] = useState(false);
  const [ user, setUser ] = useState(null); 
  const [ token , setToken ] = useState(null); 
  const setLoginStatus = (data) => {
    setLogin(data); 
    return setLogin;
  };

  const setUserData = (data) => {
      setUser(data); 
  };

  const setUserToken =( data) => {
      setToken(data); 
  }; 

    const data ={ login, user, token , setLoginStatus, setUserData, setUserToken }

    return <AuthContext.Provider value={data}>{children}</AuthContext.Provider>

}; 

