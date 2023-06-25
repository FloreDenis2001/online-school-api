import React, { useContext, useEffect, useState } from 'react'
import { ContextLogin } from '../context/LoginProvider'
import LoginContextType from '../models/LoginContextType'
import StudentService from '../services/StudentService';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import { useForm } from 'react-hook-form';
import StudentLogin from '../dtos/StudentLogin';
import { eventNames } from 'process';
import LoginRequest from '../dtos/LoginRequest';
import HttpResponse from '../models/HttpResponse';

type FormData = {
    name: string;
    email: string;
    password: string;
}

const Login: React.FC = () => {
    let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
    const { register, handleSubmit, formState: { errors } } = useForm<FormData>();

    let studentService = new StudentService();

    let navigate = useNavigate();

    let goSingUp = (): void => {
        navigate("/singup");
    }


    useEffect(()=>{
         if(studentLogin!=undefined){
            navigate(`/home/:studentLogin.studentId`);
         }
    },[]);

    let onSubmit = async (data: FormData) => {


        try {
            let rez = await studentService.logInn({ email: data.email, password: data.password });
            setStudent({
                studentId: rez['studentId'],
                email: rez['email'],
                token: rez['token'],
                firstName: rez['firstName'],
                lastName: rez['lastName']
            } as StudentLogin)

console.log("De ce nu functioneaza ! ");
           Cookies.set("authentificatedUser",JSON.stringify(studentLogin));
           
        } catch (err) {
            let result = (err as Error).message;


            console.log(result);
        }
    }


    return (
        <main className="login-container">
            <div className="main-login">

                <div className="login">
                    <form onSubmit={handleSubmit(onSubmit)}>
                        <label>Login</label>
                        <input type="email" {...register('email', { required: true, pattern: /^\S+@\S+$/i })} />
                        {errors.email && <span>Please enter a valid email address</span>}

                        <input type="password" {...register('password', { required: true, minLength: 6 })} />
                        {errors.password && <span>Password must be at least 6 characters long</span>}

                        <button type='submit'>Login</button>
                        <button onClick={goSingUp}>Sing Up</button>
                    </form>

                </div>
            </div>
        </main>
    )
}

export default Login


