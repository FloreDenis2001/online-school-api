package com.example.onlineschoolapi.services;

import com.example.onlineschoolapi.exception.StudentDosentExist;
import com.example.onlineschoolapi.model.Book;
import com.example.onlineschoolapi.model.Student;
import com.example.onlineschoolapi.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Book> getAllListByStudent(String email) {
        Optional<Student> x = studentRepo.findStudentsByEmail(email);
        if (!x.isEmpty()) {
            return x.get().getBookList();
        } else {
            throw new StudentDosentExist("Nu exista studentul respectiv ! ");
        }
    }





}