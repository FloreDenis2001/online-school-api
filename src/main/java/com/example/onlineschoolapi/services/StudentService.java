package com.example.onlineschoolapi.services;

import com.example.onlineschoolapi.dto.CreateBookRequest;
import com.example.onlineschoolapi.dto.EnrollRequestStudentToCourse;
import com.example.onlineschoolapi.exception.*;
import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Enrolment;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.CourseRepo;
import com.example.onlineschoolapi.repository.StudentRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        if (students.size() == 0) {
            throw new EmptyDataBase("DataBase Empty!");
        }
        return students;
    }

    public void enrolStudentToCourse(EnrollRequestStudentToCourse s) {

        Optional<Student> student = studentRepo.findById(s.getIdStudent());
        if (student.isEmpty()) {
            throw new StudentDosentExist("Studentul nu exista ! ");
        }

        Optional<Course> course = courseRepo.findById(s.getIdCourse());
        if (course.isEmpty()) {
            throw new StatusCourse("Cursul nu exista!");
        }


        Enrolment enrolment = new Enrolment(student.get(), course.get(), LocalDate.now());
        if (student.get().getEnrolments().contains(enrolment)) {
            throw new EnrolmentAlreadyExist("Aceste enrolment exista deja !");
        }

        student.get().addEnrolment(enrolment);

        studentRepo.saveAndFlush(student.get());
    }

    @Transactional
    @Modifying
    public void addBook(CreateBookRequest createBookRequestDTO) throws BookDosentExist, StudentDosentExist {

        Optional<Student> student = studentRepo.findById(createBookRequestDTO.getIdStudent());


        if (student.isEmpty()) {

            throw new StudentDosentExist("Student doesn't exist! ");
        }


        Book book = Book.builder().title(createBookRequestDTO.getTitle()).author(createBookRequestDTO.getAuthor()).price(createBookRequestDTO.getPrice()).build();
        if (student.get().vfExistsBook(book)) {
            throw new BookDosentExist("Book doesn't exist!");
        }

        student.get().addBook(book);
        studentRepo.saveAndFlush(student.get());
    }


}