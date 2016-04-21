package org.game.data;

import java.util.ArrayList;
import java.util.List;

public class Question {

	List<Choice> choices;

	private int point;
	String text;
	
	public Question(){
		choices = new ArrayList<>();
	}
	
	public Question(String text) {
		this();
		this.text = text;
	}
	
	public Question(String text, int point) {
		this(text);
		this.point = point;
	}
	
	public Question(List<Choice> choices, int point, String text) {
		this(text);
		this.choices = choices;
		this.point = point;
	}
	
	// abstract methods to be implemented by the subclasses
	// TODO change the method to fit String type also. Fit for both String and Number type
	// Can be written as public abstract Choice createChoice(String str, boolean isCorrect);
	// public abstract Choice createChoice(int a, int b, boolean isCorrect);
	
	// Shuffle the choices based on Fish-Yates shuffle algorithm
	public void shuffleChoices() {
		// Work with the actual instance 'choices'
		// Go in the reverse
		for(int i = choices.size() - 1; i >= 0 ; i--) {
			// random number between 0 and i-1
			int j = (int)(Math.random() * (i + 1));
			if( i != j ) {
				// Swap j and i elements in the set
				Choice choice1 = choices.get(i);
				Choice choice2 = choices.get(j);
				choices.set(i, choice2);
				choices.set(j, choice1);
			}
		}	
	}
	
	public void addChoice(Choice choice) {
		//Choice choice = createChoice();
		
		// This will prevent duplicate choices
		if(!choices.contains(choice)) {
			choices.add(choice);
		}
	}
	
	public void removeChoice(Choice choice) {
		choices.remove(choice);
	}
	
	public List<Choice> getChoices() {
		return choices;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	// check for duplicates
	public int hashCode() {
		return text.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof Question && text.equals(((Question) obj).getText()) ) {
			return true;
		}
		
		return false;
	}
}