import { useContext } from "react";
import { ContextLogin } from "../context/LoginProvider";
import Book from "../models/Book";
import HttpResponse from "../models/HttpResponse";
import LoginContextType from "../models/LoginContextType";
import BookRequest from "../models/BookRequest";

class BookService {



    api<U, T>(path: string, method: string, body: U, token?: string): Promise<HttpResponse<T>> {
        const url = "http://localhost:8080/api/v1/books" + path;
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


    allBooks = async (token : string): Promise<Book[]> => {
        let books = await this.api<null, Book[]>("/all","GET",null,token);
        if (books.status === 200) {
            let getBooks = await books.json();
            return getBooks;
        } else {
            throw new Error("Nu s-au gasit carti !");
        }
    }

    
    allMyBooks = async (token : string): Promise<Book[]> => {
        let mybooks = await this.api<null, Book[]>("/mybooks","GET",null,token);
        if (mybooks.status === 200) {
            let getMyBooks = await mybooks.json();
            return getMyBooks;
        } else {
            throw new Error("Nu s-au gasit carti !");
        }
    }


   

    deleteBook = async (token:string ,book:Book) => {
        let data = await this.api<Book,Book>("/removebyid/" + book.id,"DELETE",book,token);
        try {
          if (data.status === 200) {
            return book;
          } else {
            throw new Error("Cartea nu exista !");
          }
        } catch (e) {
          return {
            ...data,
            message: "Wrong fetch !"
          }
        }}



        updateBook = async (token:string ,book:Book) => {
          let data = await this.api<Book,Book>("/update","PUT",book,token);
          try {
            if (data.status === 200) {
              return book;
            } else {
              throw new Error("Cartea nu exista !");
            }
          } catch (e) {
            return {
              ...data,
              message: "Wrong fetch !"
            }
          }}


       
    





}

export default BookService;