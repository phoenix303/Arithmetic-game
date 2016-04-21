package org.game.common;

public interface MixedObserver extends ScoreObserver, GameObserver {
	void updateGame();
}
