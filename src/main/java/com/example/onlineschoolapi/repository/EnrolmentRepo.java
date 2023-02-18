package com.example.onlineschoolapi.repository;

import com.example.onlineschoolapi.model.Enrolemnt;
import com.example.onlineschoolapi.services.StudentService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolmentRepo extends JpaRepository<Enrolemnt,Long> {

}
