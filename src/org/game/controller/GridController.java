package org.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import org.game.domain.Car;
import org.game.domain.Coin;
import org.game.model.GameModel;
import org.game.view.GridPanel;

/**
 * Specific controller to the GridPanel to control the movement of car and coins.
 * Animation of coins and a car
 * @author divya
 *
 */
public class GridController implements KeyListener {
	
	GridPanel panel;
	GameModel model;
	
	Timer timer;
	
	public GridController(GridPanel panel, GameModel model) {
		this.panel = panel;
		this.model = model;				
	}
	
	public void init() {
		// set the car position
		model.getCar().x = 100;
		model.getCar().y = 500;

		animate();
	}

	// animate
	public void animate() {
		animateCar();
		animateCoins();
	}

	private void animateCar() {
		Car car = model.getCar();
		
		int framerate = 30; // 60 fps
		int delay = 500 / framerate;
		Timer timer = new Timer(delay, null);
		timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				car.moveHorizontally();
			}
		});
		
		car.setTimer(timer);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		Car car = model.getCar();
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			car.setVelocity(-1);
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			car.setVelocity(1);
		}

		car.start();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Car car = model.getCar();
		car.stop();
		car.setVelocity(0);
	}

	// View must ask the controller to animate
	public void animateCoins() {
		// Draw and move all the coins together
		for(int i = 0; i < model.getCoins().size(); i++) {
			moveCoin(model.getCoins().get(i));
		}
	}

	private void moveCoin(Coin coin) {
		// TODO The controller should not have any data
		int framerate = 30; // 60 fps
		int delay = 500 / framerate;
		timer = new Timer(delay, null);
		
		timer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = -1;
				if(coin.checkCollisions(model.getCar().x, model.getCar().y)) {
					index = 1;
				}
				
				if(index != -1 ) {
					model.addScore(coin.getPoint());
					model.removeQuestion();
					model.reset();
				} else if(coin.outOfScreen(panel.getWidth(), panel.getHeight())) {
					model.reset();
				}
			
				coin.moveVertically();
				
				// TODO remove this from here and call repaint at once
				panel.repaint();
			}
		});
		
		// TODO Set the velocity of the coins(velocity = 1) and not for the car(car velocity = 0 ) at the beginning of the program.
		coin.setVelocity(1);
		coin.setTimer(timer);
	}
}