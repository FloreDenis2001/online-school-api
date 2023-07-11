import React, { useContext, useEffect, useState } from 'react'
import Course from '../models/Course'
import { Image } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { DeleteOutlined, QuestionOutlined, SettingOutlined } from '@ant-design/icons';
import LoginContextType from '../models/LoginContextType';
import { ContextLogin } from '../context/LoginProvider';
import CourseService from '../services/CourseService';
import RequestService from '../services/RequestService';


interface CourseProps {
    course: Course,
}


export const CourseComp: React.FC<CourseProps> = ({ course }) => {
    const { studentLogin } = useContext(ContextLogin) as LoginContextType;
    let courseService = new CourseService();
    let requestService = new RequestService();
    let [mycourses, setMyCourses] = useState(Array<Course>);
    useEffect(() => {
        allMyCourses();
    }, []);

    let allMyCourses = async (): Promise<void> => {
        try {
            let coursesApi = await courseService.allMyCourses(studentLogin.token);
            setMyCourses(coursesApi);
        } catch (err) {
            console.log((err as Error).message);
        }
    }

    const handleAskJoined=async ():Promise<void>=>{
        try{
           await requestService.addRequest(studentLogin.token,{idStudent:studentLogin.studentId,idCourse:course.id});
        }catch(err){
            console.log('Error join book:', err);
        }
        
    }

    const isStudentCourse = mycourses.some((c) => c.id === course.id);

    return (
        <div className="course id-${course.id} " key={course.id}>
            <Image src="/img/library2.jpeg" alt="" fluid />
            <p className="describe">{course.name}</p>
            <p className="describe">{course.department}</p>
           {
               !isStudentCourse && <QuestionOutlined className='question-mark' style={{ fontSize: 20 }} onClick={handleAskJoined}/>
           }
        </div>
    )
}

export default CourseComp;