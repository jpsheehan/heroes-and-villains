package game.ui.text;

import game.Settings;
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
		
		if (team == null) {
			
			throw new NullPointerException("Team cannot be null.");
			
		}
		
		if (cityCount < Settings.getCitiesMin() || cityCount > Settings.getCitiesMax()) {
			
			throw new IllegalArgumentException(
					String.format("The number of cities must be between %d and %d (inclusive).",
							Settings.getCitiesMin(), Settings.getCitiesMax())
					);
			
		}
		
		this.cityCount = cityCount;
		this.team = team;
	}
	
	/**
	 * @return The number of cities/villains the player wishes to battle.
	 */
	public int getCityCount() {
		return this.cityCount;
	}
	
	/**
	 * @return The team the player has assembled.
	 */
	public Team getTeam() {
		return this.team;
	}
	
}
