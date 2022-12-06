import axios from 'axios'; 

const apiClient = axios.create({
    baseURL:'http://localhost:3005/photo'
});

export default apiClient; 