package org.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.game.view.ViewInterface;

public class ExitButtonListener implements ActionListener{
	ViewInterface view;
	
	public ExitButtonListener(ViewInterface view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.exit();
	}
}