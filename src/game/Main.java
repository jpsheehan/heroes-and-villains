package game;

import game.ui.gui.GraphicalUserInterface;

public class Main {

	public static void main(String[] args) {
		
		// 0L gives paper scissors rock in first building.
		// 3L gives guess the number in first building. 
		// 4L gives dice rolls in first building.
		final long seed = 3L;
		
		GeneralHelpers.seedRandom(seed);
		new GameEnvironment(GraphicalUserInterface.class).run();

	}

}
