package org.game.gametype;

import java.util.HashSet;
import java.util.Set;

import org.game.data.Choice;
import org.game.data.Question;

public abstract class Game {
	// It should be distinct questions and should not allow duplicate questions. Therefore use Set.
	//ArrayList<Question> questions;
	Set<Question> questions;
	String title;
	int totalPoint;
	// boolean isValid;
	
	public Game() {
		questions = new HashSet<>();
	}
	
	public Game(String title) {
		this();
		this.title = title;
	}
	
	public Game(String title, int totalPoint) {
		this(title);
		this.totalPoint = totalPoint;
	}
	
	public Game(Set<Question> questions, String title, int totalPoint) {
		this(title, totalPoint);
		this.questions = questions;
	}
	
	protected abstract Question createQuestion();
	
	private void addQuestion(Question q) {
		questions.add(q);
	}
	
	// add questions
	public void addQuestions(int size) {
		for(int i = 1; i <= size; i++) {
			addQuestion(createQuestion());
		}
	}
	
	public void removeQuestion(Question question) {
		questions.remove(question);
	}
	
	public Set<Question> getQuestions() {
		return questions;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getTotalPoint() {
		return totalPoint;
	}
	
	// printing questions
	public String toString() {
		String str = "";
		for(Question q: questions) {
			str += "\n" + q.getText() + "\n Choices: ";
			for(Choice choice: q.getChoices()) {
				str += choice.getText() + ", ";
			}
		}
		
		return str;
	}
}