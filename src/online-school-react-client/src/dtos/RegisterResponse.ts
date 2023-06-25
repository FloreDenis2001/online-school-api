import HttpResponse from "../models/HttpResponse";
import Student from "../models/Student";

export default interface RegisterResponse extends HttpResponse<Student> {
    studentId:number,
    age:number,
    email:string,
    firstName:string,
    lastName:string,
    password:string
 }