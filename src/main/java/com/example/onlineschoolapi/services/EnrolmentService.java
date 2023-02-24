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

    public boolean verifyDuplicateByStudentAndCourse(Enrolment e) {
        List<Enrolment> enrolmentList = enrolemmtRepo.findAll();
        boolean flag = true;
        for (Enrolment t : enrolmentList) {
            if (t.getCourse().getId() == e.getCourse().getId() && t.getStudent().getId() == e.getStudent().getId()) {
                flag = true;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    public void addEnrol(Enrolment e) throws EnrolmentAlreadyExist {
//        List<Enrolment> enrolmentList = e.getStudent().getEnrolemntsList();
//        for (Enrolment x : enrolmentList) {
//            if (x.getStudent().getId() == e.getStudent().getId() && x.getCourse().getId() == e.getCourse().getId()) {
//
//            }
//        }
    }


}
