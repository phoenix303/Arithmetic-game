package org.game.controller;

import org.game.model.GameModel;
import org.game.view.OptionPanel;

public class OptionController {
	//CarGameFrame frame; // Another frame reference
	
	OptionPanel optionframe;
	
	GameModel model;
	
	// Use this to set Background color of the frame and change the type of game
	public OptionController(OptionPanel optionframe, GameModel model) {
		
		this.optionframe = optionframe;
		this.model = model;
	}
	
	// Initialize all the startup code with respect to option panel here.
	public void init() {
		// set the model background
		model.setBackgroundColor(optionframe.getColor());		
	}
}