import React, { useState, useEffect,useContext } from 'react';
import Book from '../models/Book';
import BookService from '../services/BookService';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import BookRequest from '../models/BookRequest';
import StudentService from '../services/StudentService';

interface AddBookProps {
    handleCloseModal: () => void;
  }
  

const AddBook: React.FC<AddBookProps> = ({handleCloseModal}) => {
  const [bookTitle, setBookTitle] = useState("Title book");
  const [bookAuthor, setBookAuthor] = useState("Author book");
  const [bookPrice, setPrice] = useState(0);
  const [bookStars, setStars] = useState(0);
  const { studentLogin } = useContext(ContextLogin) as LoginContextType;
  const studentService = new StudentService();
  const handleSave = async () => {

       let book = {
        idStudent:studentLogin.studentId,
        title:bookTitle,
        author:bookAuthor,
        price:bookPrice,
        stars:bookStars
       } as BookRequest;
       await studentService.addBook(studentLogin.token,book);
       handleCloseModal();

  };

  return (
    <div className="modal-container">
      <div className="modal-content">
        <h2>Add Book</h2>
        <label htmlFor="">Title</label>
        <input type="text" name="title" value={bookTitle} onChange={(e)=>{
           setBookTitle(e.target.value);
        }} />
        <label htmlFor="">Author</label>
        <input type="text" name="author" value={bookAuthor} onChange={(e)=>{
           setBookAuthor(e.target.value);
        }} />
       <label htmlFor="">Price</label>
        <input type="number" name="price" placeholder='price' min={20} value={bookPrice} onChange={(e)=>{
          setPrice(+e.target.value);
        }} />
       <label htmlFor="">Stars</label>
        <input type="number" name="stars" max={5} min={1} value={bookStars} onChange={(e)=>{
          setStars(+e.target.value);
        }} />


        <div className="modal-buttons">
          <button className="save-button" onClick={handleSave}>Save</button>
          <button className="cancel-button" onClick={handleCloseModal}>Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default AddBook;
