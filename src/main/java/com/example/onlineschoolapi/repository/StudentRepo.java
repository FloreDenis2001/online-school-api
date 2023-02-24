package com.example.onlineschoolapi.repository;

import com.example.onlineschoolapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
    @Query("select s from Student s where s.email=?1")
    Optional<Student> findStudentsByEmail(String email);
}
