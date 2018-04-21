package game.ui;

import game.GameEnvironment;

public abstract class UserInterface {

	private GameEnvironment env;
	
	public UserInterface(GameEnvironment env) {
		
		this.env = env;
		
	}
	
	public GameEnvironment getGameEnvironment() {
		
		return this.env;
		
	}
	
	public abstract void start();
	
//	public abstract void showTitleScreen();
//	
//	public abstract Integer showGameCreationScreen() throws Exception;
//	
//	public abstract Team showTeamCreationScreen() throws Exception;
	
}
