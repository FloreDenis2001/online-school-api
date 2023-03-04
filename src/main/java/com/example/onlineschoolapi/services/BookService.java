package com.example.onlineschoolapi.services;

import com.example.onlineschoolapi.exception.BookAlreadyExist;
import com.example.onlineschoolapi.exception.EmptyDataBase;
import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        List<Book> bookList=bookRepository.findAll();

        if(bookList.size()==0){
            throw new EmptyDataBase("Baza de date este goala !");
        }

        return bookList;
    }

    public Optional<List<Book>> getAllBooksGraterPriceThan(double priceMin){
        Optional<List<Book>> bookList=bookRepository.lowestPriceBook(priceMin);
        if(bookList.isEmpty()){
            throw new EmptyDataBase("Baza de date este goala!");
        }

        return bookList;
    }

    public Optional<List<Book>> getAllBooksLowestPriceThan(double priceMax){
        Optional<List<Book>> bookList=bookRepository.highPriceBook(priceMax);
        if(bookList.isEmpty()){
            throw new EmptyDataBase("Baza de date este goala !");
        }
        return bookList;
    }


    public Optional<List<Book>> getAllBestBooks(){
           Optional<List<Book>> bookList=bookRepository.bestBooks();
           if(bookList.isEmpty()){
               throw  new EmptyDataBase("Baza de date este goala");
           }

           return bookList;
    }


    public Optional<List<Book>> getAllBooksByStars(Long stars){
        Optional<List<Book>> bookList=bookRepository.filterStarBook(stars);
        if(bookList.isEmpty()){
            throw new EmptyDataBase("Baza de date este goala !");
        }

        return bookList;
    }

    public void addBook(Book book) throws BookAlreadyExist{
        Optional<Book> findBook=bookRepository.findById(book.getId());
        if(findBook.isEmpty()){
            bookRepository.save(book);
        }else {
            throw new BookAlreadyExist("Book already exist!");
        }
    }
}
