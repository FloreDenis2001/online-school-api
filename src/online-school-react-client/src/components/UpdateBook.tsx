import React, { useState, useEffect,useContext } from 'react';
import Book from '../models/Book';
import BookService from '../services/BookService';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';

interface UpdateBookProps {
  book: Book;
  handleCloseModal: () => void;
}

const UpdateBook: React.FC<UpdateBookProps> = ({ book, handleCloseModal }) => {
  const [bookTitle, setUpdatedBookTitle] = useState(book.title);
  const [bookPrice, setUpdatedBookPrice] = useState(book.price);
  const { studentLogin } = useContext(ContextLogin) as LoginContextType;
  const bookService = new BookService();

  const handleSave = async () => {
    book.price=bookPrice;
    book.title=bookTitle;
    await bookService.updateBook(studentLogin.token,book);
    handleCloseModal();
  };

  return (
    <div className="modal-container">
      <div className="modal-content">
        <h2>Update Book</h2>
        <input type="text" name="title" value={bookTitle} onChange={(e)=>{
           setUpdatedBookTitle(e.target.value);
        }} />
     
        <input type="number" name="price" value={bookPrice} onChange={(e)=>{
          setUpdatedBookPrice(+e.target.value);
        }} />

        <div className="modal-buttons">
          <button className="save-button" onClick={handleSave}>Save</button>
          <button className="cancel-button" onClick={handleCloseModal}>Cancel</button>
        </div>
      </div>
    </div>
  );
};

export default UpdateBook;
