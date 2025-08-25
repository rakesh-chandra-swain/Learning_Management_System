package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.dto.QuizDto;
import com.nt.entity.Quiz;
import com.nt.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public QuizDto createQuiz(Quiz quiz) {
        Quiz savedQuiz = quizRepository.save(quiz);
        return new QuizDto(
                savedQuiz.getQuizTitle(),
                savedQuiz.getDescription(),
                savedQuiz.getTotalMarks()
        );
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll().stream()
                .map(q -> new QuizDto(q.getQuizTitle(), q.getDescription(), q.getTotalMarks()))
                .toList();
    }

    @Override
    public QuizDto getQuizById(Long id) {
        return quizRepository.findById(id)
                .map(q -> new QuizDto(q.getQuizTitle(), q.getDescription(), q.getTotalMarks()))
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
    }
}


