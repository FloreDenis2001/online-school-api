package com.example.onlineschoolapi.repository;


import com.example.onlineschoolapi.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {
    @Query("select c from Course c order by c.department asc")
    Optional<List<Course>> getCoursesOrderByDepartmentAsc();



}
