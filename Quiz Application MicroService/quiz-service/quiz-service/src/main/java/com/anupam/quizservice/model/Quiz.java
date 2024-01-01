package com.anupam.quizservice.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ElementCollection
	private List<Integer> questionIds;
	
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", questions=" + questionIds + ", title=" + title + "]";
	}

	public Quiz(Integer id, List<Integer> questions, String title) {
		super();
		this.id = id;
		this.questionIds = questions;
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<Integer> questions) {
		this.questionIds = questions;
	}

	private String title;
	

}
