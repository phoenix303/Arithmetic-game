package org.game.factory;

import org.game.gametype.AdditionGame;
import org.game.gametype.Game;
import org.game.gametype.MultiplicationGame;

public class SimpleFactory {
	public Game createGame(String type) {
		
		if("MULTIPLY".equals(type)) {
			return new MultiplicationGame();
		} else if("ADD".equals(type)) {
			return new AdditionGame();
		} else if("FILE".equals(type)) {
			// TODO load file questions
		}
		
		return null;
	}
}