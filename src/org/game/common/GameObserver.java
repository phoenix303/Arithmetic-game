package org.game.common;

/**
 * Game observer observes three tasks currently :
 * 1. Updating score
 * 2. Updating question and answers in the game.
 * 3. Updating when the game ends.
 * 
 * Game observer is given to the outermost frame(JFrame).
 * @author divya
 *
 */
public interface GameObserver {
	// update current score
	void updateScore();
	
	// update the current question and answers
	void update();
	
	// update when the game ends
	void updateEnd();
}
