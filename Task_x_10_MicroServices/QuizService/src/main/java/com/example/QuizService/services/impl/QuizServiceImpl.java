package com.example.QuizService.services.impl;

import com.example.QuizService.entities.Question;
import com.example.QuizService.entities.Quiz;
import com.example.QuizService.repositories.QuizRepo;
import com.example.QuizService.services.QuestionService;
import com.example.QuizService.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    QuestionService questionService;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepo.save(quiz);
    }

    @Override
    public List<Quiz> getAll() {
        List<Quiz> quiz=quizRepo.findAll();

        for (Quiz quiz1:quiz){
            List<Question> question= questionService.questionList(quiz1.getQuizId());
            quiz1.setQuestions(question);
        }

        return quiz;
    }

}
