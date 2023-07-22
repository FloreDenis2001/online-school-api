import Student from "../models/Student";

export default interface RegisterRequest{
    firstName:string,
    secondName:string,
    age:number,
    email:string,
    password:string
    role:string
 }