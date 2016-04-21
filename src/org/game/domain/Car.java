package org.game.domain;

import java.awt.geom.Rectangle2D;

import javax.swing.Timer;

//public class Car extends Rectangle2D.Double implements ActionListener, KeyListener {
public class Car extends Rectangle2D.Double {

	/**
	 * Serial UID for serialization.
	 */
	private static final long serialVersionUID = 6718734378478389L;
	private int velocity;
	private boolean visible = true;
	
	// start timer for car
	Timer timer;
	
	public Car() {
		this(10, 10);
	}
	
	public Car(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Car(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void moveHorizontally() {
		x += getVelocity();
	}
	
	public void moveVertically() {
		y += getVelocity();
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	// set the timer
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
}