package ro.mycode.onlineschoolapi.services;

import ro.mycode.onlineschoolapi.dto.CreateBookRequest;
import ro.mycode.onlineschoolapi.dto.EnrollRequestStudentToCourse;
import ro.mycode.onlineschoolapi.exception.*;
import ro.mycode.onlineschoolapi.model.Book;
import ro.mycode.onlineschoolapi.model.Course;
import ro.mycode.onlineschoolapi.model.Student;
import ro.mycode.onlineschoolapi.repository.BookRepository;
import ro.mycode.onlineschoolapi.repository.CourseRepo;
import ro.mycode.onlineschoolapi.repository.StudentRepo;
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
    public void addStudent(Student s) {
        Optional<Student> student = studentRepo.findStudentsByEmail(s.getEmail());
        if (student.isEmpty()) {
            studentRepo.save(s);
        } else {
            throw new StudentAlreadyExist("Studentul exista deja!");
        }
    }

    @Transactional
    @Modifying
    public void removeStudent(String email) {
        Optional<Student> student = studentRepo.findStudentsByEmail(email);
        if (!student.isEmpty()) {
            studentRepo.removeByEmail(email);
        } else {
            throw new StudentDosentExist("Studentul nu exista!");
        }
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

        if (!bookRepository.getBookByStudentAndTitle(student.get().getId(), book.getTitle()).isEmpty()) {
            throw new BookDosentExist("Book doesn't exist!");
        }

        student.get().addBook(book);
        studentRepo.saveAndFlush(student.get());
    }

    @Transactional
    @Modifying
    public void addCourse(EnrollRequestStudentToCourse enrollRequestStudentToCourse) throws StatusCourseException, StudentDosentExist {
        Optional<Course> course = courseRepo.findById(enrollRequestStudentToCourse.getIdCourse());
        if (course.isEmpty()) {
            throw new StatusCourseException("Cursul nu exista ! ");
        }

        Optional<Student> student = studentRepo.findById(enrollRequestStudentToCourse.getIdStudent());
        if (student.isEmpty()) {
            throw new StudentDosentExist("Studentul nu exista ! ");
        }

        List<Course> courses = student.get().getEnrolledCourses();
        for (Course t : courses) {
            if (t.equals(course.get())) {
                throw new StatusCourseException("Enrolmentul exista deja ! ");
            }
        }

        student.get().addCourse(course.get());
        studentRepo.saveAndFlush(student.get());
    }

    @Transactional
    @Modifying
    public void removeEnrolment(EnrollRequestStudentToCourse enrollRequestStudentToCourse) throws StatusCourseException, StudentDosentExist {
        Optional<Student> student = studentRepo.findById(enrollRequestStudentToCourse.getIdStudent());
        if (student.isEmpty()) {
            throw new StudentDosentExist("Studentul nu exista ! ");
        }

        Optional<Course> course = courseRepo.findById(enrollRequestStudentToCourse.getIdCourse());
        if (course.isEmpty()) {
            throw new StatusCourseException("Cursul nu exista !");
        }


        if (!student.get().vfExistCourse(course.get())) {
            throw new StatusCourseException("Enrolmentul nu exista ! ");
        } else {
            student.get().removeCourse(course.get());
            studentRepo.saveAndFlush(student.get());
        }
    }

    @Transactional
    @Modifying
    public void removeBook(CreateBookRequest createBookRequest) throws BookDosentExist, StudentDosentExist {
        Optional<Student> student = studentRepo.findById(createBookRequest.getIdStudent());

        if (student.isEmpty())
            throw new StudentDosentExist("Studentul nu exista !");

        Optional<Book> book = bookRepository.getBookByStudentAndAuthorAndTitle(createBookRequest.getIdStudent(), createBookRequest.getAuthor(),createBookRequest.getTitle());
        if (book.isEmpty())
            throw new BookDosentExist("Cartea nu exista !");
        bookRepository.removeBookByStudentAndTitle(createBookRequest.getIdStudent(), createBookRequest.getTitle());
        studentRepo.saveAndFlush(student.get());
    }

    @Transactional
    @Modifying
    public Course bestCourse() throws StatusCourseException {
        Optional<Course> curs = courseRepo.findById(studentRepo.bestCourseId().get());
        return curs.get();
    }

}