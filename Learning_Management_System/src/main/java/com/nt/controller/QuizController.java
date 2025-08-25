package com.nt.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.nt.dto.QuizDto;
import com.nt.entity.Quiz;
import com.nt.service.QuizService;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Create Quiz
    @PostMapping("/save")
    public QuizDto createQuiz(@RequestBody Quiz quiz,@RequestHeader("Authorization") String jwt) {
        return quizService.createQuiz(quiz);
    }

    // Get all quizzes
    @GetMapping("/all")
    public List<QuizDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // Get quiz by id
    @GetMapping("/{id}")
    public QuizDto getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }
}
