import React from 'react'
import { Route , Routes } from 'react-router-dom'; 
import DefaultLayout from './layout/DefaultLayout';



function App() {
  return (
  <>
    <Routes>
      <Route path='/' element={<DefaultLayout/>} />
    </Routes>
  </>
  );
};

export default App;
