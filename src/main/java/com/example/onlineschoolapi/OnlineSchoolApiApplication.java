package com.example.onlineschoolapi;

import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.BookRepository;
import com.example.onlineschoolapi.repository.CourseRepo;
import com.example.onlineschoolapi.repository.StudentRepo;
import com.example.onlineschoolapi.services.BookService;
import com.example.onlineschoolapi.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class OnlineSchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookService bookService, BookRepository bookRepository, StudentService studentService, StudentRepo studentRepo, CourseRepo courseRepo) {
        return args -> {

//          Optional<Student> s = studentRepo.findById(5L);
//
//           Student student = s.get();
//
//
//
//
//           Course curs=courseRepo.findById(3L).get();
//           student.addCourse(curs);
//           Book book=new Book(1L,"sad","title",12,5L,student);


//           student.getBooks().size();
//
//            for(int i=0;i<4;i++){
//
//                student.addBook(
//
//                        new Book().builder().author("Author"+i).price(12+i).title("titile"+i).stars(4L).build()
//                );
//            }
//
//            List<Book> books=bookService.getAllBooksGraterPriceThan(12).get();
//            for(Book x:books){
//                System.out.println(x);
//            }
//            List<Book> books=bookService.getAllBooksLowestPriceThan(15).get();
//            for(Book x:books){
//                System.out.println(x);
//            }
//            studentRepo.saveAndFlush(student);
//            List<Book> bookList= bookRepository.bestBooks().get();
//            for(Book x:bookList){
//                System.out.println(x);
//            }
//
//            List<Book> books=bookRepository.orderBooksAscendentByPrice().get();
//            for(Book x:books){
//                System.out.println(x);
//            }
//
//            List<Book> booksD=bookRepository.orderBooksDescendentByPrice().get();
//            for(Book x:booksD){
//                System.out.println(x);
//            }

//            List<Book> booksList1=bookRepository.lowestPriceBook(15).get();
//            for(Book x:booksList1){
//                System.out.println(x);
//            }
//
//            List<Book> booksList2=bookRepository.highPriceBook(15).get();
//            for(Book x:booksList2){
//                System.out.println(x);
//            }

//
//
//




        };


    }
}
