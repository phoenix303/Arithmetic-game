package org.game.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import org.game.domain.ComboBoxItem;
import org.game.model.GameModel;
import org.game.view.OptionPanel;
import org.game.view.ViewInterface;

public class OptionChangeControl implements ActionListener{

	GameModel model;
	ViewInterface view;
	
	public OptionChangeControl(ViewInterface view, GameModel model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		// set action for each combobox controller
		// Delegate this task to the controller
		Object obj = e.getSource();
		String actioncommand = e.getActionCommand();

		if(OptionPanel.COLORCHANGE.equals(actioncommand)) {
			JComboBox<ComboBoxItem<Color>> combo = (JComboBox<ComboBoxItem<Color>>) obj;	
			Color color = ((ComboBoxItem<Color>) combo.getSelectedItem() ).getValue();
			// manipulate the model and this inner class is a controller
			model.setBackgroundColor(color);
			
			OptionPanel frame = (OptionPanel) view;
			frame.updateBackgroundColor(model.getBackgoundColor());
		} else if (OptionPanel.GAMETYPE.equals(actioncommand)) {
			JComboBox<String> combo = (JComboBox<String>) obj;
			String gametype = (String) combo.getSelectedItem();
			//set model game type
			model.setGameType(gametype);
		}
	}
}