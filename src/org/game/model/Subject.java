package org.game.model;

import org.game.common.GameObserver;

public interface Subject {

	void addObserver(GameObserver observer);
	void removeObserver(GameObserver observer);
	void notifyObserver();
	void notifyScoreObservers();
	void notifyGameEndObservers();
}
