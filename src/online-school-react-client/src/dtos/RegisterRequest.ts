import Student from "../models/Student";

export default interface RegisterRequest{
    firstName:string,
    lastName:string,
    age:number,
    email:string,
    password:string
    userRole:string
 }