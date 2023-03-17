package ro.mycode.onlineschoolapi.rest;

import ro.mycode.onlineschoolapi.dto.CreateBookRequest;
import ro.mycode.onlineschoolapi.dto.EnrollRequestStudentToCourse;
import ro.mycode.onlineschoolapi.dto.StudentListResponse;
import ro.mycode.onlineschoolapi.model.Course;
import ro.mycode.onlineschoolapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/students")
public class StudentRest {

    private StudentService studentService;

    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<StudentListResponse> studentList() {
        StudentListResponse studentListResponse = StudentListResponse.builder().studentList(studentService.getAllStudents()).message("All students").build();
        return new ResponseEntity<>(studentListResponse, HttpStatus.OK);
    }

    @PostMapping("/addBook")
    public ResponseEntity<CreateBookRequest> addBookToAStudent(@RequestBody CreateBookRequest createBookRequest) {
        studentService.addBook(createBookRequest);
        return new ResponseEntity<>(createBookRequest, HttpStatus.CREATED);
    }


    @PostMapping("/enrollCourseToAStudent")
    public ResponseEntity<EnrollRequestStudentToCourse> addCourseToAStudent(@RequestBody EnrollRequestStudentToCourse enrollRequestStudentToCourse) {
        studentService.addCourse(enrollRequestStudentToCourse);
        return new ResponseEntity<>(enrollRequestStudentToCourse, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelCourse")
    public ResponseEntity<EnrollRequestStudentToCourse> cancelCourseToAStudent(@RequestBody EnrollRequestStudentToCourse enrollRequestStudentToCourse) {
        studentService.removeEnrolment(enrollRequestStudentToCourse);
        return new ResponseEntity<>(enrollRequestStudentToCourse, HttpStatus.OK);
    }

    @GetMapping("/bestCourse")
    public ResponseEntity<Course> bestCourse() {
        Course course = studentService.bestCourse();
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/cancelBook")
    public ResponseEntity<CreateBookRequest> cancelBookToAStudent(@RequestBody CreateBookRequest createBookRequest){
        studentService.removeBook(createBookRequest);
        return new ResponseEntity<>(createBookRequest,HttpStatus.OK);
    }
}







