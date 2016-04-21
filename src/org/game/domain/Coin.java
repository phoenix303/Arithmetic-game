package org.game.domain;

import java.awt.geom.Ellipse2D;

import javax.swing.Timer;

public class Coin extends Ellipse2D.Double {

	/**
	 * Serial UID for serialization.
	 */
	private static final long serialVersionUID = 6273617264346357647L;
	
	private double velocity;
	private boolean visible = true;

	String text;
	int point = 0;
	// TODO a coin should have POINTS NOT correct etc.
	//boolean correct;

	Timer timer;
	
	public Coin() {
		this(20, 20);
	}
	
	public Coin(double width, double height) {
		this(0, 0, width, height);
	}
	
	public Coin(double x, double y, double width, double height) {
		super(x, y, width, height);
	}
	
	// Move coin horizontally
	public void moveHorizontally() {
		x += getVelocity();
	}
	
	public void moveVertically() {
		y += getVelocity();
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	// Getter and setter for the text
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	// Getter and setter for points
	public void setPoint(int c) {
		point = c;
	}
	
	public int getPoint() {
		return point;
	}
	
	// set timer for the coin
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	// Add collision part
	public boolean checkCollisions(double x, double y) {
		if( x >= this.x && x < (this.x + this.height) && y >= this.y && y < (this.y + this.height)) {
			//System.out.println("Collision occured");
			return true;
		} else {
			//System.out.println("No collision");
			return false;
		}
	}
	
	// Out of screen
	public boolean outOfScreen(double x, double y) {
		if(this.x > x || this.y > y) {
			return true;
		}	
		return false;
	}
}