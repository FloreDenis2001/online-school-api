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


const Home: React.FC = () => {

  let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  let [books, setBooks] = useState(Array<Book>);
  let bookService = new BookService();
  let [courses, setCourses] = useState(Array<Course>);

  let courseService = new CourseService();
  


  useEffect(() => {
    allBooks();
    allCourses();
  }, []);

  let allBooks = async (): Promise<void> => {
    try {
      let booksApi = await bookService.allBooks(studentLogin.token);
      setBooks(booksApi);
    } catch (err) {
      console.log((err as Error).message);
    }
  }

  let allCourses = async (): Promise<void> => {
    try {
      let coursesApi = await courseService.allCourses(studentLogin.token);
      setCourses(coursesApi);
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
            books.map(book => {
              return <BookComp book={book} />
            })
          }

          { 
            courses.map(courses => {
              return <CourseComp course={courses} />
            })
          }
        </div>

      </main>

      <Footer />

    </>
  )
}

export default Home