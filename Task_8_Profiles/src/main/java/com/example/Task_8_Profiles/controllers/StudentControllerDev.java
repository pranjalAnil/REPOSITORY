package com.example.Task_8_Profiles.controllers;

import com.example.Task_8_Profiles.entity.StudentDev;
import com.example.Task_8_Profiles.entity.StudentProd;
import com.example.Task_8_Profiles.repositories.StudentDevRepo;
import com.example.Task_8_Profiles.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stud")
@Profile("dev")
public class StudentControllerDev {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody StudentDev studentDev){
        System.out.println("data Added in dev");
        return new ResponseEntity<>(studentService.addDev(studentDev), HttpStatus.OK);
    }
}
