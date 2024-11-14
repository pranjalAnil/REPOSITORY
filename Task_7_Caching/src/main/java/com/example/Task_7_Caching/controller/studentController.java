package com.example.Task_7_Caching.controller;

import com.example.Task_7_Caching.payloads.StudentDto;
import com.example.Task_7_Caching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class studentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto){
        StudentDto studentDto1=studentService.add(studentDto);
        return new ResponseEntity<>(studentDto1, HttpStatus.CREATED);

    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id){
        return new ResponseEntity<>(studentService.getById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        return new ResponseEntity<>(studentService.deleteStud(id),HttpStatus.OK);
    }

    @PutMapping("/updateById/{id}")
    private ResponseEntity<?> updateById(@PathVariable int id,@RequestBody StudentDto studentDto){
        return new ResponseEntity<>(studentService.updateById(id,studentDto),HttpStatus.OK);

    }




}
