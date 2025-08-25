package com.nt.service;

import java.util.List;

import com.nt.entity.Question;

public interface QuestionService {
    Question addQuestionToQuiz(Long quizId, Question question);
    List<Question> getQuestionsByQuiz(Long quizId);
}

