import axios from 'axios'; 

const apiClient = axios.create({
    baseURL:'https://63838d506e6c83b7a9968e05.mockapi.io/api'
});

export default apiClient; 