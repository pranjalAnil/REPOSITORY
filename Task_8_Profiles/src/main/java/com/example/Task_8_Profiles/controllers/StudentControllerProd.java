package com.example.Task_8_Profiles.controllers;

import com.example.Task_8_Profiles.entity.StudentDev;
import com.example.Task_8_Profiles.entity.StudentProd;
import com.example.Task_8_Profiles.repositories.StudentProdRepo;
import com.example.Task_8_Profiles.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stud")
@Profile("prod")
public class StudentControllerProd {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody StudentProd studentProd){
        System.out.println("data Added in prod");
        return new ResponseEntity<>(studentService.addProd(studentProd), HttpStatus.OK);
    }
}
