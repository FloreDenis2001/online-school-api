package com.example.onlineschoolapi.services;


import com.example.onlineschoolapi.exception.EnrolmentAlreadyExist;
import com.example.onlineschoolapi.model.Enrolment;
import com.example.onlineschoolapi.repository.EnrolmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class EnrolmentService {

    private EnrolmentRepo enrolemmtRepo;


    public EnrolmentService(EnrolmentRepo enrolemmtRepo) {
        this.enrolemmtRepo = enrolemmtRepo;
    }




}
