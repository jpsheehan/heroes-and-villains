package game.character;

import static game.GeneralHelpers.getString;

import java.io.Serializable;

import game.city.CityType;
import game.minigame.*;

/**
 * Represents a Villain as described in section 3.3 of the specification.
 */
public class Villain extends Character implements Serializable {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -8995085959168801055L;

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
	 * @return The number of wins required to defeat the Villain.
	 */
	public int getNumberOfWinsToDefeat() {
		
		return Villain.numberOfRequiredWins - this.timesBeaten;
		
	}
	
	/**
	 * @return The Villain's taunt phrase (depends on Villain Type).
	 */
	public String getTaunt() {
		
		return taunt;
		
	}
	
	/**
	 * @return The type of minigame the Villain likes to play.
	 */
	public MinigameType getFavouriteGame() {
		
		return favouriteGame;
		
	}

	/**
	 * Updates the number of times the Villain has been beaten.
	 * @throws VillainDeadException if the Villain dies.
	 */
	public void beat() {
		
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
	
	/**
	 * @return The number of times the Hero must beat the Villain in total.
	 */
	public static int getNumberOfRequiredWins() {
		
		return Villain.numberOfRequiredWins;
		
	}
	
}
