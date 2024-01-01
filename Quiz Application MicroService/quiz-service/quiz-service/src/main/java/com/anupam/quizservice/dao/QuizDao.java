package com.anupam.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anupam.quizservice.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{

}
