package game.ui;

import game.character.Hero;

public abstract class UserInterface {

	public abstract void showTitleScreen();
	
	public abstract Integer showGameCreationScreen() throws Exception;
	
	public abstract Hero[] showTeamCreationScreen() throws Exception;
	
}
