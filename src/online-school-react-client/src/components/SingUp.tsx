import React, { useContext, useState } from 'react'
import { ContextLogin } from '../context/LoginProvider'
import LoginContextType from '../models/LoginContextType'
import StudentService from '../services/StudentService';
import { useNavigate } from 'react-router-dom';
import RegisterRequest from '../dtos/RegisterRequest';



const SingUp: React.FC = () => {
    let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
    const [email, setEmail] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [password, setPassword] = useState("");
    const [age, setAge] = useState(0);

     let studentService=new StudentService();

     let sing = async () =>{
         let rez= await studentService.singUp({firstName,lastName,age,email,password} as RegisterRequest);
     }
    let navigate = useNavigate();
    
    let goLogin = (): void => {
        navigate("/login");
    }
    return (
        <main className="sing-up-container">
        <div className="main-sing">

            <div className="signup">
                <form>
                    <label htmlFor="chk" aria-hidden="true">Sign up</label>
                    <input type="text" name="txt" placeholder="First name" value={firstName} onChange={(e)=>{
                            setFirstName(e.target.value);
                        }}/>
                    <input type="text" name="txt" placeholder="Last name" value={lastName} onChange={(e)=>{
                            setLastName(e.target.value);
                        }}/>
                    <input type="number" min="18" max="25" name="txt" placeholder="Age" value={age} onChange={(e)=>{
                            setAge(+e.target.value);
                        }}/>

                    <input type="email" name="email" placeholder="Email" value={email} onChange={(e)=>{
                            setEmail(e.target.value);
                        }} />
                    <input type="password" name="pswd" placeholder="Password" value={password} onChange={(e)=>{
                            setPassword(e.target.value);
                        }}/>
                    <button onClick={sing}>Sign up</button>
                    <button onClick={goLogin}>Login</button>
                </form>
            </div>

        </div>
    </main>
    )
}

export default SingUp