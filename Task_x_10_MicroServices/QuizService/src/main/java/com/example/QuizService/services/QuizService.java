package com.example.QuizService.services;

import com.example.QuizService.entities.Quiz;

import java.util.List;

public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public List<Quiz> getAll();
}
