package game;

import game.ui.text.TextUserInterface;

public class Main {

	public static void main(String[] args) {
		
		new GameEnvironment(TextUserInterface.class).run();

	}

}
