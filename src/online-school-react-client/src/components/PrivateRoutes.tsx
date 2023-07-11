import { useContext } from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';



const PrivateRoutes = () => {
    let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;


  return (
    studentLogin.studentId!==0? <Outlet/> : <Navigate to='/'/>
  )
}

export default PrivateRoutes;