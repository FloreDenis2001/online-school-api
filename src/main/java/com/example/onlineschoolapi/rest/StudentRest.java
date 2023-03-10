package com.example.onlineschoolapi.rest;

import com.example.onlineschoolapi.dto.CreateBookRequest;
import com.example.onlineschoolapi.dto.EnrollRequestStudentToCourse;
import com.example.onlineschoolapi.dto.StudentListResponse;
import com.example.onlineschoolapi.exception.BookAlreadyExist;
import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.services.StudentService;
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







