package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

