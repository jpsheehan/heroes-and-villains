package game.ui.text;

import game.Team;

/**
 * A class that contains all the information required for beginning a new game.
 * @author jesse
 *
 */
public class NewGameParameters {

	/**
	 * The number of cities/villains the player wishes to battle.
	 */
	private int cityCount;
	
	/**
	 * The team the player has assembled.
	 */
	private Team team;
	
	/**
	 * Creates a new NewGameParameters instance.
	 * @param cityCount The number of cities the player wants to fight through.
	 * @param team The team of heroes the player has chosen.
	 */
	public NewGameParameters (int cityCount, Team team) {
		this.cityCount = cityCount;
		this.team = team;
	}
	
	/**
	 * Returns the number of cities/villains the player wishes to battle.
	 * @return
	 */
	public int getCityCount() {
		return this.cityCount;
	}
	
	/**
	 * Returns the team the player has assembled.
	 * @return
	 */
	public Team getTeam() {
		return this.team;
	}
	
}
