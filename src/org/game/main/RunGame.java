package org.game.main;

import javax.swing.SwingUtilities;

import org.game.model.GameModel;
import org.game.view.GameFrame;

public class RunGame {
	public static void main(String[] args) {	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GameFrame(new GameModel());
			}
		});
	}
}