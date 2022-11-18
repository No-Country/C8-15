import React from 'react';
import Web from '../components/web/Web'
import Landing from '../components/Landing/Landing'
import { Outlet } from 'react-router';



const DefaultLayout = () => {
  return (
    <>
      <Web/>
      <Landing/>
      <Outlet/>
    </>
  );
};

export default DefaultLayout