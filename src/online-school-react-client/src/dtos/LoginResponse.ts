import HttpResponse from "../models/HttpResponse";
import StudentLogin from "./StudentLogin";

export default interface LoginResponse extends HttpResponse<StudentLogin> {
   studentId:number,
   email:string,
   token:string,
   firstName:string,
   lastName:string,
   userRole:string
}