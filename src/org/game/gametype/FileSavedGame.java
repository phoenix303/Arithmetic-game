package org.game.gametype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Loads game from a text file
 * @author divya
 *
 */
public class FileSavedGame {
	
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String number1 = reader.readLine();

	}
}