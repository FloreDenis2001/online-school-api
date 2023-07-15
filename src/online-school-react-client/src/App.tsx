import React, { useEffect } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes, useNavigate } from "react-router-dom";
import Home from './components/Home';
import Login from './components/Login';
import LoginProvider from './context/LoginProvider';
import SingUp from './components/SingUp';
import PrivateRoutes from './components/PrivateRoutes';
import MyCourses from './components/MyCourses';
import MyBooks from './components/MyBooks';
import Profile from './components/Profile';
import RequestAdmin from './components/RequestAdmin';

function App() {

  return (
    <div className="App">


      <BrowserRouter>
        <LoginProvider>
          <Routes>
          <Route  path='/' element={<Login />} />
            <Route path="/singup" element={<SingUp />} /> 
            <Route path='/swagger/index.html'/>
            <Route path="" element={<PrivateRoutes />}>
              <Route path="/home" element={<Home/>}/>
              <Route path='/mycourses/:studentId' element={<MyCourses/>}/>
              <Route path='/mybooks/:studentId' element={<MyBooks/>}/>
              <Route path='/profile/:studentId' element={<Profile/>}/>
              <Route path='/requests' element={<RequestAdmin/>}/>
            </Route>
         </Routes>
        </LoginProvider>
      </BrowserRouter> 


    </div>
  );
}

export default App;
