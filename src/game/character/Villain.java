package game.character;

import static game.GeneralHelpers.getString;

import game.city.CityType;
import game.minigame.*;


/**
 * Represents a Villain as described in section 3.3 of the specification.
 */
public class Villain extends Character {

	/**
 	* The numbers of wins required to defeat the Villain.
 	*/
	private static int numberOfRequiredWins = 3;

	/**
 	* The numbers of wins required to beat the Villain.
 	*/
	private int timesBeaten;
	
	/**
	 * The villain's taunt phrase.
	 */
	private String taunt;
	
	/**
	 * Favourite game (if only one entry) or (multiple) games the Villain plays.
	 */
	private MinigameType favouriteGame;
	
	/**
	 * Creates an instance of Villain.
	 * @param name The Villain's name.
	 * @param taunt The Villain's taunt phrase.
	 * @param game The Villain's favourite game to play.
	 */
	public Villain(String name, String taunt, MinigameType game) {
		
		super(name);
		
		this.timesBeaten = 0;
		this.favouriteGame = game;
		this.taunt = taunt;
	}

	/**
	 * Returns the number of wins required to defeat the Villain.
	 * @return
	 */
	public int getNumberOfWinsToDefeat() {
		
		return Villain.numberOfRequiredWins - this.timesBeaten;
		
	}
	
	/**
	 * Returns the Villain's taunt phrase (depends on Villain Type).
	 * Currently hardcoded in this class to Villain Type.
	 * @return
	 */
	public String getTaunt() {
		
		return taunt;
		
	}
	
	/**
	 * Returns (single) favourite game or (multiple) games the Villain plays.
	 * @return
	 */
	public MinigameType getFavouriteGame() {
		
		return favouriteGame;
		
	}

	/**
	 * Updates the number of times the Villain has been beaten.
	 * @return
	 * @throws VillainDeadException 
	 */
	public void beat() throws VillainDeadException {
		
		this.timesBeaten++;
		
		if (this.timesBeaten >= Villain.numberOfRequiredWins) {
			
			this.timesBeaten = Villain.numberOfRequiredWins;
			
		}
	}
	
	/**
	 * Creates a new instance from the strings.json file.
	 * @param type The city that the Villain belongs to.
	 * @return A new instance of Villain.
	 */
	public static Villain fromStringsFile(CityType type) {
		
		String buildingName = type.getProperName();
		String name = getString(String.format("%s.Villain.Name", buildingName));
		String taunt = getString(String.format("%s.Villain.Taunt", buildingName));
		MinigameType game = MinigameType.fromProperName(getString(String.format("%s.Villain.Game", buildingName)));
		
		return new Villain(name, taunt, game);
		
	}
	
}
