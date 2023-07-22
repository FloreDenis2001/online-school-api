import HttpResponse from "../models/HttpResponse";
import Student from "../models/Student";

export default interface CourseRequest{
    name:string,
    department:string,
    studentId:number
}