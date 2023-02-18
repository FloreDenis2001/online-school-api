package com.example.onlineschoolapi;

import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.StudentRepo;
import com.example.onlineschoolapi.services.StudentService;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class OnlineSchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentService studentService, StudentRepo studentRepo) {
        return args -> {
            Optional<Student> s = studentRepo.findStudentsByEmail("denis@yahoo.com");

            System.out.println(s.get());
            Student student = s.get();

            for (int i = 0; i < 10; i++) {


                student.addBook(Book.builder()
                        .bookName("bookName " + i)
                        .createdAt(LocalDate.now().minusYears(i))
                        .build());




            }




            studentRepo.saveAndFlush(student);

            List<Book> bookListT=studentService.getAllListByStudent("denis@yahoo.com");

          for(Book x : bookListT){
              System.out.println(x);
          }






        };





    }
}
