package game;

import game.ui.gui.GraphicalUserInterface;

public class Main {

	public static void main(String[] args) {
		
		new GameEnvironment(GraphicalUserInterface.class).run();

	}

}
