import React, { useContext, useEffect, useState } from 'react'
import Header from './Header'
import Footer from './Footer'
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import BookService from '../services/BookService';
import { BookComp } from './BookComp';
import Book from '../models/Book';
import CourseService from '../services/CourseService';
import Course from '../models/Course';
import CourseComp from './CourseComp';
import LoginResponse from '../dtos/LoginResponse';


const MyCourses: React.FC = () => {

  let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  let bookService = new BookService();
  let [mycourses, setMyCourses] = useState(Array<Course>);
  let courseService = new CourseService();
  


  useEffect(() => {
    allMyCourses();
  }, []);




  let allMyCourses = async (): Promise<void> => {
    try {
      let coursesApi = await courseService.allMyCourses(studentLogin.token);
      console.log(coursesApi);
      setMyCourses(coursesApi);
    } catch (err) {
      console.log((err as Error).message);
    }
  }


  return (
    <>

      <Header />

      <main>
        <div className="all-learnings">

          { 
            mycourses.map(mycourse => {
              return <CourseComp course={mycourse} />
            })
          }
        </div>

      </main>

      <Footer />

    </>
  )
}

export default MyCourses