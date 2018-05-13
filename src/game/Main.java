package game;

import game.ui.gui.GraphicalUserInterface;

public class Main {

	public static void main(String[] args) {
		
		GeneralHelpers.seedRandom(0L);
		new GameEnvironment(GraphicalUserInterface.class).run();

	}

}
