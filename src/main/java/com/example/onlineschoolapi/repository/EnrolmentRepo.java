package com.example.onlineschoolapi.repository;

import com.example.onlineschoolapi.model.Course;
import com.example.onlineschoolapi.model.Enrolment;
import com.example.onlineschoolapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentRepo extends JpaRepository<Enrolment,Long> {
    @Query("select s from Enrolment s where s.student=?1 and s.course=?2")
    Optional<Enrolment> findEnrolments(Long idStudent, Long IdCourse);


    @Query("select s from Enrolment s where s.course.id=?1 and s.student.id=?2")
    Optional<Enrolment> findEnrolmentsByStudentAndCourse(Long idCourse,Long idStudent);

    @Modifying
    @Transactional
    @Query ("delete from Enrolment  s where s.course.id=?1 and s.student.id=?2")
    void removeEnrolment(Long idCourse,Long idStudent);
}

