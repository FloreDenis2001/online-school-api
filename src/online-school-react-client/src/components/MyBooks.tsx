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


const MyBooks: React.FC = () => {

  let { studentLogin, setStudent } = useContext(ContextLogin) as LoginContextType;
  let bookService = new BookService();
  let [mybooks, setMyBooks] = useState(Array<Book>);


  useEffect(() => {
    allMyBooks();
  }, []);

  let allMyBooks= async (): Promise<void> => {
    try {
      let coursesApi = await bookService.allMyBooks(studentLogin.token);
      setMyBooks(coursesApi);
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
            mybooks.map(mybooks => {
              return <BookComp book={mybooks}/>
            })
          }
        </div>
      </main>
      <Footer />

    </>
  )
}

export default MyBooks