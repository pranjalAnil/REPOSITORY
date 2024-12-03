package com.example.QuizService.controller;

import com.example.QuizService.entities.Quiz;
import com.example.QuizService.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.addQuiz(quiz), HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuiz(){
        return new ResponseEntity<>(quizService.getAll(),HttpStatus.OK);
    }
}
