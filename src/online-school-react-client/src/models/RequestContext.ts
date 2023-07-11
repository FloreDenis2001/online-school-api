export default interface RequestContext{
    id:number,
    courseId:number,
    studentId:number,
    status:string,
    studentEmail?:string,
    courseName?:string
}