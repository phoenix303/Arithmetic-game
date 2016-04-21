package org.game.data;

/*
 * Choice class is an instance of an answer
 */
public class Choice {
	
	// TODO add question reference. This would be helpful for adding score
	Question question;
	
	String text;
	boolean isCorrect;
	
	// Constructor
	public Choice() {}
	
	public Choice(Question question, String text, boolean isCorrect) {
		this.question = question;
		this.text = text;
		this.isCorrect = isCorrect;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public boolean getCorrect() {
		return isCorrect;
	}
	
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	// To check whether two choice are equal or not. Override equals() and hashcode()
	public int hashCode() {
		return this.text.hashCode();
	}
	
	public boolean equals(Object obj) {
		if( obj instanceof Choice && this.text.equals(((Choice) obj).getText()) ) {
			return true;
		}

		return false;
	}
}