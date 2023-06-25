import React, { useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import Home from './components/Home';
import Header from './components/Header';
import Footer from './components/Footer';
import Login from './components/Login';
import LoginProvider from './context/LoginProvider';
import SingUp from './components/SingUp';
import PrivateRoutes from './components/PrivateRoutes';
import FormExample from './components/FormExample';
import MyCourses from './components/MyCourses';
import MyBooks from './components/MyBooks';
import Profile from './components/Profile';

function App() {

  return (
    <div className="App">


      <BrowserRouter>
        <LoginProvider>
          <Routes>
            <Route element={<PrivateRoutes />}>
              <Route path="/home/:studentId" element={<Home/>}/>
              <Route path='/mycourses/:studentId' element={<MyCourses/>}/>
              <Route path='/mybooks/:studentId' element={<MyBooks/>}/>
              <Route path='/profile/:studentId' element={<Profile/>}/>
            </Route>
        
              <Route path='/' element={<Login />} />
              <Route path='/login' element={<Login />} />
              <Route path="/singup" element={<SingUp />} /> 
         </Routes>
        </LoginProvider>
      </BrowserRouter> 


    </div>
  );
}

export default App;
