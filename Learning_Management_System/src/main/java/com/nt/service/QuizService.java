package com.nt.service;

import java.util.List;

import com.nt.dto.QuizDto;
import com.nt.entity.Quiz;

public interface QuizService {
	QuizDto createQuiz(Quiz quiz);
    List<QuizDto> getAllQuizzes();
    QuizDto getQuizById(Long id);
}

