package com.example.onlineschoolapi.services;

import com.example.onlineschoolapi.dto.CreateBookRequest;
import com.example.onlineschoolapi.dto.CreateBookResponse;
import com.example.onlineschoolapi.dto.EnrollRequestStudentToCourse;
import com.example.onlineschoolapi.exception.*;
import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.BookRepository;
import com.example.onlineschoolapi.repository.CourseRepo;
import com.example.onlineschoolapi.repository.StudentRepo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
public class StudentService {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;

    private BookRepository bookRepository;


    public StudentService(StudentRepo studentRepo, CourseRepo courseRepo, BookRepository bookRepository) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.bookRepository = bookRepository;
    }

    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        if (students.size() == 0) {
            throw new EmptyDataBase("DataBase Empty!");
        }
        return students;
    }


    @Transactional
    @Modifying
    public void addBook(CreateBookRequest createBookRequestDTO) throws BookDosentExist, StudentDosentExist {

        Optional<Student> student = studentRepo.findById(createBookRequestDTO.getIdStudent());


        if (student.isEmpty()) {
            throw new StudentDosentExist("Student doesn't exist! ");
        }


        Book book = Book.builder().
                title(createBookRequestDTO.getTitle()).
                author(createBookRequestDTO.getAuthor())
                .price(createBookRequestDTO.getPrice()).stars(createBookRequestDTO.getStars()).build();

        System.out.println(book);
        if (!bookRepository.getBookByStudentAndTitle(student.get().getId(), book.getTitle()).isEmpty()) {
            throw new BookDosentExist("Book doesn't exist!");
        }

        student.get().addBook(book);
        studentRepo.saveAndFlush(student.get());
    }

    @Transactional
    @Modifying
    public void addCourse(EnrollRequestStudentToCourse enrollRequestStudentToCourse) throws StatusCourse, StudentDosentExist {
        Course course = courseRepo.findById(enrollRequestStudentToCourse.getIdCourse()).get();
        if (course == null) {
            throw new StatusCourse("Cursul nu exista ! ");
        }

        Student student = studentRepo.findById(enrollRequestStudentToCourse.getIdStudent()).get();
        if (student == null) {
            throw new StudentDosentExist("Studentul nu exista ! ");
        }

        List<Course> courses = student.getEnrolledCourses();
        for (Course x : courses) {
            if (x.equals(course)) {
                throw new StatusCourse("Enrolmentul exista deja ! ");
            }
        }

        student.addCourse(course);
        studentRepo.saveAndFlush(student);
    }

    @Transactional
    @Modifying
    public void removeEnrolment(EnrollRequestStudentToCourse enrollRequestStudentToCourse) throws StatusCourse, StudentDosentExist {
        Optional<Student> student = studentRepo.findById(enrollRequestStudentToCourse.getIdStudent());
        if (student.isEmpty()) {
            throw new StudentDosentExist("Studentul nu exista ! ");
        }

        Optional<Course> course = courseRepo.findById(enrollRequestStudentToCourse.getIdCourse());
        if (course.isEmpty()) {
            throw new StatusCourse("Cursul nu exista !");
        }


        if (!student.get().vfExistCourse(course.get())) {
            throw new StatusCourse("Enrolmentul nu exista ! ");
        } else {
            student.get().removeCourse(course.get());
            studentRepo.saveAndFlush(student.get());
        }
    }

    @Transactional
    @Modifying
    public void removeBook(CreateBookRequest createBookRequest) throws BookDosentExist,StudentDosentExist{
           Optional<Student > student=studentRepo.findById(createBookRequest.getIdStudent());

           if(student.isEmpty()){
               throw new StudentDosentExist("Studentul nu exista !");
           }

          List<Book> books=student.get().getBooks();

    }
    
    @Transactional
    @Modifying
    public Course bestCourse() throws StatusCourse{
        Optional<Course> curs =courseRepo.findById(studentRepo.bestCourseId().get());
         return curs.get();
    }

}