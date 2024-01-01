package com.anupam.quizservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anupam.quizservice.dao.QuizDao;
import com.anupam.quizservice.feign.QuizInterface;
import com.anupam.quizservice.model.QuestionWrapper;
import com.anupam.quizservice.model.Quiz;
import com.anupam.quizservice.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;

//	@Autowired
//	QuestionDao questionDao;
//	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> question=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(question);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Succes",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		Quiz quiz=quizDao.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionForUser=quizInterface.getQuestionsFromId(questionIds);
		return questionForUser;
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		
		
		return score;
	}
	
}
