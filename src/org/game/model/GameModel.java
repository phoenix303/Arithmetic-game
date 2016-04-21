package org.game.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Set;

import org.game.common.GameObserver;
import org.game.data.Question;
import org.game.domain.Car;
import org.game.domain.Coin;
import org.game.domain.Strip;
import org.game.factory.SimpleFactory;
import org.game.gametype.Game;



// NOTE : Model can have all the independent classes or data like coin, car, question, etc
public class GameModel implements Subject {
	ArrayList<GameObserver> observers;

	// TODO Define the road width. USE these values for view.
	public static int ROAD_WIDTH = 50;
	public static int ROAD_HEIGHT = 50;

	// add roadside object
	Strip strip;

	// Has JPanel which consists of questions and Answers
	public int score;
	
	// TODO use the combo box value to set 'type'
	String type = "MULTIPLY"; // Default string type
	
	boolean gameActive = true;
	
	// Object used to hit
	Car car;
	
	ArrayList<Coin> coins;
	int distancebetweencoins = 50;
	
	// This model is for 2 choices
	int noofchoices = 2;
	
	// Has set of Questions
	Game game;
	
	// Number of questions to create
	int noofquestions = 2;
	
	// backgroundColor
	Color backgroundcolor = Color.GREEN;
	
	// set current question
	Question question;
	
	// TODO Define a class user which has score and name as a field
	// String user name
	String name;
	
	public GameModel() {
		this.score = 0;
		this.gameActive = true;
		
		// TODO use the observer pattern to notify view of the change
		observers = new ArrayList<>();
	}
	
	// The model job at the beginning
	// To create a set of questions
	public void init() {
		// create car and coins and media strip
		this.car = new Car();
		this.coins = new ArrayList<>();
		this.strip = new Strip();
		
		// Simple factory
		game = new SimpleFactory().createGame(getGameType());
		
		// TODO add this part to the game class which creates the list of questions there.
		// I do not care what type of game it is, just create 10 questions of the type.
		// Ask the Multiplication OR Addition class game itself to make questions.
		game.addQuestions(getTotalQuestions());

		// TODO pre define the ROAD width and height. Then divide the width into no. of choices
		for(int i = 0; i < noofchoices; i++) {
			distancebetweencoins = (i + 2) * distancebetweencoins;
			Coin coin = new Coin(30, 30);
			coin.x = distancebetweencoins;
			coins.add(coin);
		}
		
		// Set the question
		// setQuestion();
	}

	@Override
	public void addObserver(GameObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(GameObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver() {
		for(GameObserver o: observers) {
			o.update();
		}
	}
	
	@Override
	public void notifyScoreObservers() {
		for(GameObserver o : observers) {
			o.updateScore();
		}
	}
	
	@Override
	public void notifyGameEndObservers() {
		for(GameObserver o : observers) {
			o.updateScore();
			o.updateEnd();
		}
	}
	
	// Getter and Setter for a name
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	//set RoadSide
	public void setRoadSide(Strip roadside) {
		this.strip = roadside;
	}
	// get roadside
	public Strip getRoadSide() {
		return strip;
	}
	
	// scroll strips
	public void scroll() {
		strip.scroll();
	}
	
	// get car
	public Car getCar() {
		return car;
	}
	
	// get left coin
	public ArrayList<Coin> getCoins() {
		return coins;
	}
	
	// Deal with score
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	// The view should listen if the score is changed then it refreshes the view
	public void addScore(int score) {
		this.score += score;
		notifyScoreObservers();
	}
	
	// Is game active
	public boolean isGameActive() {
		return gameActive;
	}
	
	public void setGameActive(boolean active) {
		this.gameActive = active;
	}
	
	// Game type
	public String getGameType() {
		return type;
	}
	
	public void setGameType(String type) {
		this.type = type;
	}
	
	public void setTotalQuestions(int n) {
		this.noofquestions = n;
	}
	
	public int getTotalQuestions() {
		return noofquestions;
	}
	
	// Game getter and setter
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Game getGame() {
		return game;
	}
	
	// Read the current Question
	public Question getQuestion() {
		return this.question;
	}
	
	// TODO since we have game whch has questions, we should NOT create methods or instance variables related to questions
	// Whenever I have to set a new question, I have to set coin text or choices too.
	public void setQuestion() {
		if(!game.getQuestions().isEmpty()) {
			this.question = game.getQuestions().iterator().next();
			setCoin();
		} else {
			// If the question set is empty, stop the animation/game and update the screen
			gameActive = false;
		}
	}
	
	public void setCoin() {
		// Coin should not be defined before
		for(int i = 0; i < question.getChoices().size(); i++) {
			coins.get(i).setText(getQuestion().getChoices().get(i).getText());
			if(question.getChoices().get(i).getCorrect() == true) {
				coins.get(i).setPoint(getQuestion().getPoint());
			} else {
				coins.get(i).setPoint(0);
			}
		}
	}
	
	// TODO CHANGE THIS TO DELETE LAST ELEMENT of the Question set
	public void removeQuestion() {
		Set<Question> questions = game.getQuestions();
		questions.remove(getQuestion());
		setQuestion();
		// when removing question notfiy observers
		if(gameActive) {
			notifyObserver();
		} else {
			notifyGameEndObservers();
		}	
	}
	
	public void setBackgroundColor(Color color) {
		this.backgroundcolor = color;
	}
	
	public Color getBackgoundColor() {
		return backgroundcolor;
	}
	
	public void startAnimation() {
		for(Coin coin: coins) {
			coin.start();
		}

		strip.start();
	}
	
	// Reset all the coins
	public void reset() {
		for(Coin coin: coins) {
			reset(coin);
		}
	}
	
	// reset the coins position
	private void reset(Coin coin) {
		coin.y = -10;
		coin.setVisible(true);
	}
}
