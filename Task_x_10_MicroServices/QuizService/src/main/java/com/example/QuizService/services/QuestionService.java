package com.example.QuizService.services;

import com.example.QuizService.entities.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "question-service", url = "http://localhost:8080")
public interface QuestionService {
    @GetMapping("/question/getByQuizID/{quizId}")
    public List<Question> questionList(@PathVariable int quizId);
}
