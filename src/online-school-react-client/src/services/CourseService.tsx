import LoginResponse from "../dtos/LoginResponse";
import Course from "../models/Course";
import HttpResponse from "../models/HttpResponse";
import React from "react";

class CourseService {


    api<U, T>(path: string, method: string, body: U, token?: string): Promise<HttpResponse<T>> {
        const url = "http://localhost:8080/api/v1/courses" + path;
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

    allCourses = async (token : string): Promise<Course[]> => {
        let courses = await this.api<null, Course[]>("/all","GET",null,token);
        if (courses.status === 200) {
            let getCourses = await courses.json();
            return getCourses;
        } else {
            throw new Error("Nu s-au gasit cursuri !");
        }
    }

    allMyCourses = async (token : string): Promise<Course[]> => {
        let mycourses = await this.api<null, Course[]>("/my/courses","GET",null,token);
        if (mycourses.status === 200) {
            let getMyCourses = await mycourses.json();
            return getMyCourses;
        } else {
            throw new Error("Nu s-au gasit cursuri !");
        }
    }
}

export default CourseService;