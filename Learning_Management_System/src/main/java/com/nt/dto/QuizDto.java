package com.nt.dto;


public class QuizDto {

    private String quizTitle;
    private String description;
    private Integer totalMarks;

    public QuizDto(String quizTitle, String description, Integer totalMarks) {
        this.quizTitle = quizTitle;
        this.description = description;
        this.totalMarks = totalMarks;
    }

    // Getters
    public String getQuizTitle() {
        return quizTitle;
    }
    public String getDescription() {
        return description;
    }
    public Integer getTotalMarks() {
        return totalMarks;
    }
}

