import React, { Suspense }from 'react'
import { Navigate, Route , Routes } from 'react-router-dom'; 
const Home = React.lazy(()=> import ('./views/Home/Home'));
const RegisterPage = React.lazy(()=> import ('./components/register/Register'));
const LoginPage = React.lazy(()=> import ('./views/login/login'))
const Error404 = React.lazy(()=> import ('./views/error/Error404'))


function App() {
  return (
  <>
  <Suspense>
    <Routes>
      <Route path='/' element={<Home/>} />
      <Route path='register' element={<RegisterPage/>} />
      <Route path='login' element={<LoginPage/>} />
      <Route path='404' element={<Error404/>}/>
    </Routes>
    </Suspense>
  </>
  );
};

export default App;
