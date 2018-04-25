package game;

import game.ui.text.TextUserInterface;

public class Main {

	public static void main(String[] args) {
		
		GeneralHelpers.setIsRunningInEclipse(true);
		new GameEnvironment(TextUserInterface.class).run();

	}

}
