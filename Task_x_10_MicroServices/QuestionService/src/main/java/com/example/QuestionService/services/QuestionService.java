package com.example.QuestionService.services;

import com.example.QuestionService.entities.Question;

import java.util.List;

public interface QuestionService {
    public Question addQuestion(Question question);
    public List<Question> getAllQuestions();
    public List<Question> getByQuizNumber(int quizId);

}
