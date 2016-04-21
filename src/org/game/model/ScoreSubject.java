package org.game.model;

import org.game.common.ScoreObserver;

public interface ScoreSubject {
	void addObserver(ScoreObserver observer);
	void removeObserver(ScoreObserver observer);
	void notifyScoreObservers();
}
