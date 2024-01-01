package com.anupam.questionservice.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anupam.questionservice.model.Question;
import com.anupam.questionservice.model.QuestionWrapper;
import com.anupam.questionservice.model.Response;
import com.anupam.questionservice.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	Logger logger = LoggerFactory.getLogger(QuestionController.class);


	@GetMapping("/allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestion();
	}
	
	@GetMapping("category/{category}")
	public List<Question> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
		
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question){
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteQuestionById(@PathVariable int id) {
		return questionService.deleteQuestionById(id);
	}
	
	@GetMapping("get/{id}")
	public Optional<Question> getQuestionById(@PathVariable int id) {
		return questionService.getQuestionById(id);
	}
	
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz
	(@RequestParam String categoryName,@RequestParam Integer numQuestions){
		return questionService.getQuestionsForQuiz(categoryName,numQuestions);
	}
	
	@PostMapping("getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids){
		return questionService.getQuestionFromId(questionids);
	}
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionService.getScore(responses);
	}
}
