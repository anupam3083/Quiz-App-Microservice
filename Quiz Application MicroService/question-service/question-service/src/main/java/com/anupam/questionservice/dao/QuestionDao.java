package com.anupam.questionservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anupam.questionservice.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	public List<Question> findQuestionByCategory(String category);

	@Query(value="SELECT q.id FROM question q where q.category=:category ORDER BY RAND() LIMIT :numQ",nativeQuery = true)
	public List<Integer> findRandomQuestionByCategory(String category, int numQ);
	
	/*
	 * @Query(value =
	 * "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ"
	 * , nativeQuery = true) List<Question> getRandomQuestions(@Param("category")
	 * String category, @Param("numQ") int numQ);
	 */
}
