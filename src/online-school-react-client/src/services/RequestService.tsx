import LoginResponse from "../dtos/LoginResponse";
import RequestDTO from "../dtos/RequestDTO";
import StudentLogin from "../dtos/StudentLogin";
import Course from "../models/Course";
import HttpResponse from "../models/HttpResponse";
import React from "react";
import RequestContext from "../models/RequestContext";

class CourseService {


    api<U, T>(path: string, method: string, body: U, token?: string): Promise<HttpResponse<T>> {
        const url = "http://localhost:8080/api/v1/request" + path;
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

    allRequest = async (token : string): Promise<RequestContext[]> => {
        let requests = await this.api<null, RequestContext[]>("/all","GET",null,token);
        if (requests.status === 200) {
            let getRequests = await requests.json();
            return getRequests;
        } else {
            throw new Error("Nu s-au gasit request-uri !");
        }
    }

    addRequest = async (token : string,requestDTO:RequestDTO) => {
        let data = await this.api<RequestDTO,RequestDTO>("/addRequest","POST",requestDTO,token);
        try {
            if (data.status === 200) {
              return requestDTO;
            } else {
              throw new Error("Request-ul exista deja !");
            }
          } catch (e) {
            return {
              ...data,
              message: "Wrong fetch !"
        }
     }}

     acceptRequest = async(token:string , requestContext:RequestContext)=>{
         let data = await this.api<RequestContext,RequestContext>("/accept","PUT",requestContext,token);
         try {
          if (data.status === 200) {
            return requestContext;
          } else {
            throw new Error("Request-ul exista deja !");
          }
        } catch (e) {
          return {
            ...data,
            message: "Wrong fetch !"
      }
     }
       }


       
     deniedRequest = async(token:string , requestContext:RequestContext)=>{
      let data = await this.api<RequestContext,RequestContext>("/denied","PUT",requestContext,token);
      try {
       if (data.status === 200) {
         return requestContext;
       } else {
         throw new Error("Request-ul nu exista  !");
       }
     } catch (e) {
       return {
         ...data,
         message: "Wrong fetch !"
   }
  }
    }
}

export default CourseService;