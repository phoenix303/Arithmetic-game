package org.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.game.model.GameModel;
import org.game.view.OptionPanel;

public class StartButtonListener implements ActionListener {
	OptionPanel view; // Changed from View Interface
	GameModel model;
	
	public StartButtonListener(OptionPanel view, GameModel model) {
		this.view = view;
		this.model = model;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// start the model
		// set the value of name in the option panel
		model.setName(view.getValue());
		model.init();
		model.setQuestion();
		view.start();
		//model.notifyObserver();
		// start animation
		model.startAnimation();
	}
}
