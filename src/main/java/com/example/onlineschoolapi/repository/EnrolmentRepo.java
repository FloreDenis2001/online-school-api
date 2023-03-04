package com.example.onlineschoolapi.repository;

import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Enrolment;
import com.example.onlineschoolapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepo extends JpaRepository<Enrolment,Long> {
    @Query("select s from Enrolment s where s.student=?1 and s.course=?2")
    Optional<Enrolment> findEnrolments(Long idStudent, Long IdCourse);
}

