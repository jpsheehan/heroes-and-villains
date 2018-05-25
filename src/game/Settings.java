package game;

import static game.GeneralHelpers.*;

import java.nio.file.Paths;

/**
 * A class to hold the game settings as loaded from the "strings.json" file.
 * @author jesse
 *
 */
public class Settings {

	/**
	 * Denoted by "Game.Cities.Min" in strings.json.
	 * @return The minimum number of cities (inclusive) that the team can fight through.
	 */
	public static int getCitiesMin() {
		
		return getInt("Game.Cities.Min");
		
	}
	
	/**
	 * Denoted by "Game.Cities.Max" in strings.json.
	 * @return The maximum number of cities (inclusive) that the team can fight through.
	 */
	public static int getCitiesMax() {
		
		return getInt("Game.Cities.Max");
		
	}
	
	/**
	 * Denoted by "Game.Heroes.Min" in strings.json.
	 * @return The minimum number of heroes (inclusive) that the team must have.
	 */
	public static int getHeroesMin() {
		
		return getInt("Game.Heroes.Min");
		
	}
	
	/**
	 * Denoted by "Game.Heroes.Max" in strings.json.
	 * @return The maximum number of heroes (inclusive) that the team must have.
	 */
	public static int getHeroesMax() {
		
		return getInt("Game.Heroes.Max");
		
	}
	
	/**
	 * Denoted by "Game.Team.Name.Min" in strings.json.
	 * @return The minimum number of characters the team name can have.
	 */
	public static int getTeamNameMin() {
		
		return getInt("Game.Team.Name.Min");
		
	}

	/**
	 * Denoted by "Game.Team.Name.Max" in strings.json.
	 * @return The maximum number of characters the team name can have.
	 */
	public static int getTeamNameMax() {
		
		return getInt("Game.Team.Name.Max");
		
	}

	/**
	 * Denoted by "Game.Hero.Name.Min" in strings.json.
	 * @return The minimum number of characters the hero name can have.
	 */
	public static int getHeroNameMin() {
		
		return getInt("Game.Hero.Name.Min");
		
	}

	/**
	 * Denoted by "Game.Hero.Name.Max" in strings.json.
	 * @return The maximum number of characters the hero name can have.
	 */
	public static int getHeroNameMax() {
		
		return getInt("Game.Hero.Name.Max");
		
	}
	
	/**
	 * Denoted by "Game.Team.StartMoney" in strings.json.
	 * @return The amount of money the team starts with.
	 */
	public static int getTeamStartMoney() {
		
		return getInt("Game.Team.StartMoney");
		
	}
	
	/**
	 * Denoted by "Game.SaveDirectory" in the strings.json file and prepended with the user's home directory.
	 * @return The directory to which save states can be written to and read from.
	 */
	public static String getSaveDirectory() {
		
		return Paths.get(System.getProperty("user.home"), getString("Game.SaveDirectory")).toString();
		
	}
	
	/**
	 * Denoted by "Game.ImagesDirectory" in the strings.json file. 
	 * @return The directory name (within the JAR) where images are loaded from.
	 */
	public static String getImagesDirectory() {
		
		return getString("Game.ImagesDirectory");
		
	}
}
