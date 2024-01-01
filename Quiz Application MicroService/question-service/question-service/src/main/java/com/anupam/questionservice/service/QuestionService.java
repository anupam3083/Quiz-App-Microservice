package com.anupam.questionservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.anupam.questionservice.dao.QuestionDao;
import com.anupam.questionservice.model.Question;
import com.anupam.questionservice.model.QuestionWrapper;
import com.anupam.questionservice.model.Response;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestion(){
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
	}

	public Optional<Question> getQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionDao.findById(id);
	}

	public List<Question> getQuestionByCategory(String category) {
		// TODO Auto-generated method stub
		return questionDao.findQuestionByCategory(category);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		// TODO Auto-generated method stub
		questionDao.save(question);
			return new ResponseEntity<>("Success",HttpStatus.CREATED);
		
	}

	public String deleteQuestionById(int id) {
		// TODO Auto-generated method stub
		questionDao.deleteById(id);
		
		return "Question Deleted";
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		// TODO Auto-generated method stub
		List<Integer> questions= questionDao.findRandomQuestionByCategory(categoryName,numQuestions);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Integer> questionids) {
		
		List<QuestionWrapper> wrappers=new ArrayList<>();
		
		List<Question> questions=new ArrayList<>();
		
		for(Integer id:questionids) {
			questions.add(questionDao.findById(id).get());
		}
		for(Question question:questions) {
			QuestionWrapper wrapper=new QuestionWrapper();
			wrapper.setId(question.getId());
			wrapper.setQuestionTitle(question.getQuestionTitle());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {

		int right=0;
		for(Response res:responses) {
			Question question=questionDao.findById(res.getId()).get();
			if(res.getResponse().equals(question.getCorrectAns())){
				right+=1;
			}
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
}
