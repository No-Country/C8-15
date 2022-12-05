import apiClient from '../services/apiRest';


export const postRegisterAxios = async (values) => {
  try{
    const { data } = await apiClient.post('/users', values);
    return data;
  } catch ({ err }){
    return err.message;
  }
};

export const postLoginAxios = async ( values ) => {
  try{
    const { data } = await apiClient.post('/auth/login', values );
    return data;
  } catch ({ err }) {
    return  err.message; 
  }
}; 