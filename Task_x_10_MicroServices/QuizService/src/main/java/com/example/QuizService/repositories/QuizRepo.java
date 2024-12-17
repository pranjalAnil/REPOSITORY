package com.example.QuizService.repositories;

import com.example.QuizService.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepo extends JpaRepository<Quiz,Integer> {
    List<Quiz> findByQuizId(int quizId);
}
