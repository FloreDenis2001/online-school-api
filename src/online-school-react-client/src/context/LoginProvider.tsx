import React, { useEffect } from "react";
import LoginContextType from "../models/LoginContextType";
import { createContext, useState } from "react";
import Student from "../models/Student";
import Cookies from "js-cookie";
import LoginResponse from "../dtos/LoginResponse";
import StudentLogin from "../dtos/StudentLogin";

type Props = {
  children?: React.ReactNode;
}

export const ContextLogin = createContext<LoginContextType | null>(null);

const LoginProvider: React.FC<Props> = ({ children }) => {
  const [studentLogin, setStudent] = useState<StudentLogin>({
    studentId: 0,
    email: 'NOEMAIL',
    token: 'INVALIDE',
    firstName: 'NONAME',
    lastName: 'NONAME',
    userRole:"NOROLE"
  });

  useEffect(() => {
    const authentificatedUser = Cookies.get("authentificatedUser");
    if (authentificatedUser) {
      setStudent(JSON.parse(authentificatedUser) as StudentLogin);
    }
  }, []);

  function seteazaStudent(studentLogin: StudentLogin): void {
    setStudent(studentLogin);
  }

  return <ContextLogin.Provider value={{ studentLogin, setStudent }} >  {children}  </ContextLogin.Provider>
}

export default LoginProvider