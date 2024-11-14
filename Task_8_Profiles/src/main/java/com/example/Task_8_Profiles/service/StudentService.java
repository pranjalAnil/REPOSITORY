package com.example.Task_8_Profiles.service;

import com.example.Task_8_Profiles.entity.StudentDev;
import com.example.Task_8_Profiles.entity.StudentProd;
import com.example.Task_8_Profiles.repositories.StudentDevRepo;
import com.example.Task_8_Profiles.repositories.StudentProdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDevRepo studentDevRepo;

    @Autowired
    private StudentProdRepo studentProdRepo;

    public StudentProd addProd(StudentProd studentProd){
        return studentProdRepo.save(studentProd);
    }

    public Object addDev(StudentDev studentDev) {
        return studentDevRepo.save(studentDev);
    }
}
