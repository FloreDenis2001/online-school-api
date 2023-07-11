import React, { useContext, useEffect, useState } from 'react';
import Book from '../models/Book';
import { getStars } from '../utile/Utile';
import { Image } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { DeleteOutlined, SettingOutlined } from '@ant-design/icons';
import BookService from '../services/BookService';
import { ContextLogin } from '../context/LoginProvider';
import LoginContextType from '../models/LoginContextType';
import UpdateBook from './UpdateBook';

interface BookCompProps {
    book: Book;
}

export const BookComp: React.FC<BookCompProps> = ({ book }) => {
    const { studentLogin } = useContext(ContextLogin) as LoginContextType;
    const bookService = new BookService();
    const [mybooks, setMyBooks] = useState<Book[]>([]);
    const [showUpdateModal, setShowUpdateModal] = useState(false);
    
    useEffect(() => {
        allMyBooks();
    }, []);

    const allMyBooks = async (): Promise<void> => {
        try {
            const booksApi = await bookService.allMyBooks(studentLogin.token);
            setMyBooks(booksApi);
        } catch (err) {
            console.log((err as Error).message);
        }
    };

    const isStudentBook = mybooks.some((c) => c.id === book.id);

    const handleUpdateBook = (): void => {
        setShowUpdateModal(true);
    };

    const handleCloseModal = (): void => {
        setShowUpdateModal(false);
    };

    const handleDeleteBook=async ():Promise<void>=>{
        try{
        await bookService.deleteBook(studentLogin.token,book);
        window.location.reload()
        }catch(err){
            console.log('Error deleting book:', err);
        }
        
    }

    return (
        <div className={`book id-${book.id}`} key={book.id}>
            <Image src="/img/library2.jpeg" alt="" fluid />
            <p className="describe">{book.title}</p>
            <p className="price">{book.price} RON</p>
            <p className="stars">{getStars(book)}</p>
            <div className="icon-book">
            {isStudentBook && (
                <p className="settings">
                    <SettingOutlined style={{ fontSize: 20 }} onClick={handleUpdateBook} />
                </p>
            )}
            {isStudentBook && (
                <p className="settings">
                    <DeleteOutlined style={{ fontSize: 20 }} onClick={handleDeleteBook}/>
                </p>
            )}

            </div>

            {showUpdateModal && (
                <UpdateBook book={book} handleCloseModal={handleCloseModal} />
            )}
        </div>
    );
};

export default BookComp;
