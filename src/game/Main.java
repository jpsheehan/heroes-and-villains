package game;

import game.ui.gui.GraphicalUserInterface;

/**
 * The main class to be run from the JAR file.
 * @author jesse
 *
 */
public class Main {

	/**
	 * Starts the game.
	 * @param args These do nothing.
	 */
	public static void main(String[] args) {
		
		GraphicalUserInterface gui = new GraphicalUserInterface(false);
		gui.start();

	}

}
