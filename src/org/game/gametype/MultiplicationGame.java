package org.game.gametype;

import java.util.Set;

import org.game.data.Choice;
import org.game.data.Question;

public class MultiplicationGame extends Game {
	
	public MultiplicationGame() {
		super();
	}

	public MultiplicationGame(String title) {
		super(title);
	}
	
	public MultiplicationGame(String title, int totalPoint) {
		super(title, totalPoint);
	}
	
	public MultiplicationGame(Set<Question> questions, String title, int totalPoint) {
		super(questions, title, totalPoint);
	}
	
	@Override
	protected Question createQuestion() {
		// Single digit multilpication numbers between 0 - 9
		int a = (int)(Math.random() * 10) + 1;
		int b = (int)(Math.random() * 10) + 1;
		String questionText = a + " x " + b;
		
		Question question = new Question();
		question.setText(questionText);
		question.setPoint(1);
		
		Choice choice = new Choice(question, String.valueOf(a * b), true);//behaviour.createChoice(a, b, true);
		question.addChoice(choice);
		
		int i = 2;
		if(a == 0) a = 1;
		int c = a;
		if(b == 0) b = 1;
		int d = b;
		// Created a second choice (2 choices)
		while( i > question.getChoices().size()) {
			if( c == 0 || d == 0) {
				c = a;
				d = b;
			}
			
			c = (int) ((Math.random() * (c + c)) + 1);
			d = (int) ((Math.random() * (d + d)) + 1);
			choice = new Choice(question, String.valueOf(c * d), false);
			question.addChoice(choice);
		}
		
		// shuffle choices
		question.shuffleChoices();
		
		// Adding points to the game
		this.totalPoint += question.getPoint();
		
		return question;
	}
}