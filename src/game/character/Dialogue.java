package game.character;

import game.GeneralHelpers;
import game.city.CityType;
 
/**
 * Provides the dialogue for the InnKeeper 
 *  
 */
public class Dialogue {
	
//	/**
//	 * vPlate message
//	 */
//	private String vPlate;
//	
	/**
	 * Greeting message when team enters inn 
	 */
	private String greeting;
	
	/**
	 * Farewell message when team leaves inn 
	 */
	private String farewell;
	
	/**
	 * Message when the team is presented with a list of items.
	 */
	private String options;
	
	/**
	 * Message when the team purchases an item.
	 */
	private String purchase;	 
	
	 public Dialogue(CityType type) {
		 
		 String specifier = type.getProperName() + ".InnKeeper.Dialogue";

		 this.greeting = GeneralHelpers.getString(specifier + ".Greeting");
		 this.farewell = GeneralHelpers.getString(specifier + ".Farewell");
		 this.options = GeneralHelpers.getString(specifier + ".Options");
		 this.purchase = GeneralHelpers.getString(specifier + ".Purchase");
		 
	 }
	
	/**
	 * @return The message to display when the team enters the shop
	 */
	public String getGreeting() {
		return greeting;
	}
	
	/**
	 * @return The message to display when the team exits the shop
	 */
	public String getFarewell() {
		return farewell;
	}

	/**
	 * @return The message to display when the team is presented with a list of items to buy
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @return The message to display when the team purchases an item
	 */
	public String getPurchase() {
		return purchase;
	}
	
}
