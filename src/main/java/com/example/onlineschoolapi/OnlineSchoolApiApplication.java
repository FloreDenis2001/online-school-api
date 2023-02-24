package com.example.onlineschoolapi;

import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Enrolment;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.CourseRepo;
import com.example.onlineschoolapi.repository.StudentRepo;
import com.example.onlineschoolapi.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class OnlineSchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSchoolApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentService studentService, StudentRepo studentRepo, CourseRepo courseRepo) {
        return args -> {
//            Optional<Student> s = studentRepo.findStudentsByEmail("denis@yahoo.com");
//
//            System.out.println(s.get());
//            Student student = s.get();
//            student.initLazyCollection();
//
//
//            Course curs=courseRepo.findById(3L).get();
//
//
//            Enrolment enrolment = Enrolment.builder().course(curs).createAt(LocalDate.now()).build();
//
//            student.addEnrol(enrolment);
//            Set<Enrolment> enrolments =student.getEnrolemntsList();
//            for(Enrolment x : enrolments){
//                System.out.println(x);
//            }

//            studentRepo.saveAndFlush(student);


        };


    }
}
