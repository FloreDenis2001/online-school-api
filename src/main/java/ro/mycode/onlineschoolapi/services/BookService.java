package ro.mycode.onlineschoolapi.services;

import ro.mycode.onlineschoolapi.exception.EmptyDataBase;
import ro.mycode.onlineschoolapi.exception.StudentDosentExist;
import ro.mycode.onlineschoolapi.model.Book;
import ro.mycode.onlineschoolapi.model.Student;
import ro.mycode.onlineschoolapi.repository.BookRepository;
import org.springframework.stereotype.Service;
import ro.mycode.onlineschoolapi.repository.StudentRepo;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private StudentRepo studentRepo;

    public BookService(BookRepository bookRepository,StudentRepo studentRepo) {
        this.bookRepository = bookRepository;
        this.studentRepo=studentRepo;
    }

    public List<Book> getAllBooks(){
        List<Book> bookList=bookRepository.findAll();

        if(bookList.size()==0){
            throw new EmptyDataBase("Baza de date este goala !");
        }

        return bookList;
    }

    public List<Book> getAllBooksByStudentEmail(String email){
        Student student=studentRepo.findStudentsByEmail(email).get();
        if(student!=null && student.getBooks()!=null){
            return student.getBooks();
        }else{
            throw new StudentDosentExist("Nu s-au gasit date valabile!");
        }

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


}
