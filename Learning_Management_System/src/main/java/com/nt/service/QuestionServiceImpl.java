package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.entity.Question;
import com.nt.entity.Quiz;
import com.nt.repository.QuestionRepository;
import com.nt.repository.QuizRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Question addQuestionToQuiz(Long quizId, Question question) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + quizId));
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id " + quizId));
        return quiz.getQuestions();
    }
}

