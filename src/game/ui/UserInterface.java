package game.ui;

import game.Team;

public abstract class UserInterface {

	public abstract void showTitleScreen();
	
	public abstract Integer showGameCreationScreen() throws Exception;
	
	public abstract Team showTeamCreationScreen() throws Exception;
	
}
