package com.example.onlineschoolapi.rest;

import com.example.onlineschoolapi.dto.StudentListResponse;
import com.example.onlineschoolapi.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
