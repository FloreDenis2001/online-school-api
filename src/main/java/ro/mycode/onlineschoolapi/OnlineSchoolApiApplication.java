package ro.mycode.onlineschoolapi;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ro.mycode.onlineschoolapi.repository.BookRepository;
import ro.mycode.onlineschoolapi.repository.CourseRepo;
import ro.mycode.onlineschoolapi.repository.StudentRepo;
import ro.mycode.onlineschoolapi.services.BookService;
import ro.mycode.onlineschoolapi.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collections;

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

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
