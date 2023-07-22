import HttpResponse from "../models/HttpResponse";
import React from "react";
import Student from "../models/Student"
import LoginResponse from "../dtos/LoginResponse";
import LoginRequest from "../dtos/LoginRequest";
import { ZhihuCircleFilled } from "@ant-design/icons";
import RegisterResponse from "../dtos/RegisterResponse";
import RegisterRequest from "../dtos/RegisterRequest";
import BookRequest from "../models/BookRequest";
import CourseRequest from "../dtos/CourseRequest";
class StudentService {

    api<U, T>(path: string, method: string, body: U, token?: string): Promise<HttpResponse<T>> {
        const url = "http://localhost:8080/api/v1/students" + path;
        const options: RequestInit = {
            method,
            headers: {
                "Content-Type": "application/json;charset=utf-8",
                "Authorization": `Bearer ${token}`,
              },
            body: body == null ? null : JSON.stringify(body),
        };

        return fetch(url, options);
    }

    logInn = async (loginRequest: LoginRequest): Promise<LoginResponse> => {
        const response = await this.api<LoginRequest, LoginResponse>("/login", "POST", loginRequest,"");
        if (response.status === 200) {
            return response.json();
        }else {
            throw new Error("Credentialele nu sunt corecte");
        }
    }

    singUp = async (registerRequest: RegisterRequest): Promise<RegisterResponse> => {
        const response = await this.api<RegisterRequest, RegisterResponse>("/register", "POST", registerRequest,"");
        return response.json();
    }
    
    addBook = async (token:string ,book:BookRequest) => {
        let data = await this.api<BookRequest,BookRequest>("/addBook","POST",book,token);
        try {
          if (data.status === 200) {
            return book;
          } else {
            throw new Error("Cartea exista deja !");
          }
        } catch (e) {
          return {
            ...data,
            message: "Wrong fetch !"
          }
     }}

     addCourse = async (token:string , course:CourseRequest) => {
      let data = await this.api<CourseRequest,CourseRequest>("/enrollCourseToAStudent","POST",course,token);
      try {
        if (data.status === 200) {
          return course;
        } else {
          throw new Error("Cursul exista deja !");
        }
      } catch (e) {
        return {
          ...data,
          message: "Wrong fetch !"
        }
   }}

      


}

export default StudentService;