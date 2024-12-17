package com.example.QuestionService.contorller;

import com.example.QuestionService.entities.Question;
import com.example.QuestionService.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @PostMapping("/add")
    public ResponseEntity<?> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.OK);
    }
    @GetMapping("/getByQuizID/{quizId}")
    public ResponseEntity<?> getQuestionByQuizID(@PathVariable int quizId){
        return new ResponseEntity<>(questionService.getByQuizNumber(quizId),HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(),HttpStatus.OK);
    }

}
