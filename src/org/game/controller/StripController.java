package org.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.game.model.GameModel;
import org.game.view.RoadStripPane;

// Position the coins and the car
public class StripController {

	GameModel model;
	
	RoadStripPane frame;
	
	//public CarGameController(CarGameFrame frame, RoadModel model) {
	public StripController(RoadStripPane frame, GameModel model) {
		this.frame = frame;
		this.model = model;
	}
	
	public void init() {		
		animate();
	}
	
	public void animate() {
		int animationTime = 500;
		int fps = 30;
		int delay = animationTime / fps;
				
		Timer timer = new Timer(delay, null);
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.scroll();
				frame.repaint();
			}
		});
		
		// set the timer
		model.getRoadSide().setTimer(timer);
	}
}
