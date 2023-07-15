import React, { useContext, useEffect, useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { DeleteOutlined, SettingOutlined } from '@ant-design/icons';
import BookService from '../services/BookService';
import LoginContextType from '../models/LoginContextType';
import UpdateBook from './UpdateBook';
import RequestContext from '../models/RequestContext';
import RequestService from '../services/RequestService';
import RequestDTO from '../dtos/RequestDTO';
import { ContextLogin } from '../context/LoginProvider';
import Student from '../models/Student';
interface RequestCompProps {
    request: RequestContext;
}

export const RequestComp: React.FC<RequestCompProps> = ({ request }) => {
   
  let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  let requestService = new RequestService();
  let [isAdmin,setAdmin]=useState(false);
  const [studentRequest,Student] = useState<Student>({
    id:0,
    age:0,
    email:"noemail",
    first_name:"noname",
    second_name:"noname",
    userRole:"norole"
  });

  
  useEffect(() => {
    handleRole();   
  }, []);



  const handleDenied=async ():Promise<void>=>{
    try{
      let data=  await requestService.deniedRequest(studentLogin.token,request);
      
      console.log(data);
    }catch(err){
        console.log('Error denied request:', err);
    }
    
}

    const handleAccept=async ():Promise<void>=>{
        try{
            await requestService.acceptRequest(studentLogin.token,request);
        }catch(err){
            console.log('Error accept request:', err);
        }
        
    }

    
    const handleRole=async ():Promise<void>=>{
        if(studentLogin.userRole==="ADMIN"){
              setAdmin(true);
        }
    }

    return (
        <>
         {isAdmin &&
         <div className={`request id-${request.id}`} key={request.id}>
            <p className='courseId'>Student : {request.studentEmail}</p>
            <p className='studentId'>Course : {request.courseName}</p>
            <button onClick={handleAccept}>Accept</button>
            <button onClick={handleDenied}>Denied</button>
         </div>
}</>
       
    );
};

export default RequestComp;
