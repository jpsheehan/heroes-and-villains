package game.character;

import java.io.Serializable;

import game.GeneralHelpers;
import game.city.CityType;
 
/**
 * Provides the dialogue for the InnKeeper 
 *  
 */
public class Dialogue implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -4548151420650313683L;

	/**
	 * Greeting message when team enters inn / shop
	 */
	private String greeting;
	
	/**
	 * Message when the team purchases an item.
	 */
	private String purchase;
	
	/**
	 * The string to display when the team leaves the shop.
	 */
	private String farewell;
	
	/**
	 * Creates a new instance of Dialogue.
	 * @param type The type of city to create the Dialogue for.
	 */
	public Dialogue(CityType type) {
		 
		 String specifier = type.getProperName() + ".InnKeeper.Dialogue";

		 this.greeting = GeneralHelpers.getString(specifier + ".Greeting");
		 this.purchase = GeneralHelpers.getString(specifier + ".Purchase");
		 this.farewell = GeneralHelpers.getString(specifier + ".Farewell");
		 
	 }
	
	/**
	 * @return The message to display when the team enters the shop
	 */
	public String getGreeting() {
		return greeting;
	}

	/**
	 * @return The message to display when the team purchases an item
	 */
	public String getPurchase() {
		return purchase;
	}
	
	/**
	 * @return The message to display when the team leaves the shop.
	 */
	public String getFarewell() {
		return farewell;
	}
	
}
