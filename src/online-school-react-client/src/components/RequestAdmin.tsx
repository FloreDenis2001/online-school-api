import React, { useContext, useEffect, useState } from 'react'
import Header from './Header'
import Footer from './Footer'
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import RequestService from '../services/RequestService';
import RequestDTO from '../dtos/RequestDTO';
import RequestComp from './RequestComp';
import RequestContext from '../models/RequestContext';
import StudentService from '../services/StudentService';
import Student from '../models/Student';


const RequestAdmin: React.FC = () => {

  let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  let requestService = new RequestService();
  let [requests, setRequests] = useState(Array<RequestContext>);
  let studentService= new StudentService();
  let [studentData,setStudentData] = useState<Student>({
    id:0,
    age:0,
    email:"noemail",
    first_name:"noname",
    second_name:"noname",
    userRole:"norole"
  });


  useEffect(() => {
    allRequests();
  }, []);

  let allRequests= async (): Promise<void> => {
    try {
      let data = await requestService.allRequest(studentLogin.token);
      setRequests(data);
    } catch (err) {
      console.log((err as Error).message);
    }
  }

  return (
    <>
      <Header />

      <main>
        <div className="all-requests">
        
          { 
            requests.map(request => {
              if(request.status===null){
              return <RequestComp request={request}/>}
            })
          }
        </div>
      </main>
      <Footer />

    </>
  )
}

export default RequestAdmin