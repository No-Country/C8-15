import React from 'react';
import Navbar from '../../components/web/navbar/Navbar'
import Footer from '../../components/web/footer/Footer';
import Landing from '../../components/Landing/Landing'
import { Outlet } from 'react-router';



const Home = () => {
  return (
    <main>
      <Navbar/>
      <Landing/>
      <Outlet/>
      <Footer/>
    </main>
  );
};

export default Home;