import StudentLogin from "../dtos/StudentLogin";
import Student from "./Student";
import React from "react";

type LoginContextType={
    studentLogin:StudentLogin;
    setStudent:(studentLogin:StudentLogin)=>void;
}

export default LoginContextType;