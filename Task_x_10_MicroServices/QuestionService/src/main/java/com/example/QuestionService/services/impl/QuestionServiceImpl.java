package com.example.QuestionService.services.impl;

import com.example.QuestionService.entities.Question;
import com.example.QuestionService.repositories.QuestionRepo;
import com.example.QuestionService.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepo questionRepo;
    @Override
    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {

        return questionRepo.findAll();
    }

    @Override
    public List<Question> getByQuizNumber(int quizId) {
        return questionRepo.findByQuizId(quizId);
    }


}
