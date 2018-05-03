package game;

import static game.GeneralHelpers.*;

/**
 * A class to hold the game settings as loaded from the "strings.json" file.
 * @author jesse
 *
 */
public class Settings {

	/**
	 * Gets the minimum number of cities (inclusive) that the team can fight through.
	 * Denoted by "Game.Cities.Min" in strings.json.
	 * @return
	 */
	public static int getCitiesMin() {
		
		return getInt("Game.Cities.Min");
		
	}
	
	/**
	 * Gets the maximum number of cities (inclusive) that the team can fight through.
	 * Denoted by "Game.Cities.Max" in strings.json.
	 * @return
	 */
	public static int getCitiesMax() {
		
		return getInt("Game.Cities.Max");
		
	}
	
	/**
	 * Gets the minimum number of heroes (inclusive) that the team must have.
	 * Denoted by "Game.Heroes.Min" in strings.json.
	 * @return
	 */
	public static int getHeroesMin() {
		
		return getInt("Game.Heroes.Min");
		
	}
	
	/**
	 * Gets the maximum number of heroes (inclusive) that the team must have.
	 * Denoted by "Game.Heroes.Max" in strings.json.
	 * @return
	 */
	public static int getHeroesMax() {
		
		return getInt("Game.Heroes.Max");
		
	}
	
	public static int getTeamNameMin() {
		
		return getInt("Game.Team.Name.Min");
		
	}
	
	public static int getTeamNameMax() {
		
		return getInt("Game.Team.Name.Max");
		
	}
	
	public static int getHeroNameMin() {
		
		return getInt("Game.Hero.Name.Min");
		
	}
	
	public static int getHeroNameMax() {
		
		return getInt("Game.Hero.Name.Max");
		
	}
}
