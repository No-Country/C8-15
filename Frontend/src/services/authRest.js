import apiClient from "./apiRest";


const register = (payload) =>{
  return apiClient.post('/register',payload);
}
const login = (payload) => {
  return apiClient.post('/login',payload);
}

export default {
  register, login 
}