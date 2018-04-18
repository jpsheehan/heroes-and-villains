package game.character;

//20180416 to do
// dialogue is just for InnKeeper

//20180417
//A fixed and hardcoded list of phrases, will be concatenated with specific team lists, 
//	perhaps in future a list of phrases for each category and randomly select per Inn / InnKeeper 
 
/**
 * Provides the dialogue for the InnKeeper 
 *  
 */
public class Dialogue {
	
	/**
	 * vPlate message
	 */
	private String vPlate;
	
	/**
	 * Greeting message when team enters inn 
	 */
	private String greeting;
	
	/**
	 * Farewell message when team leaves inn 
	 */
	private String farewell;
	
	/**
	 * Provides text list of options / things that can be done in the inn
	 */
	private String options;
	
	/**
	 * Provide text list of things that can be purchased
	 */
	private String purchase;
	
	/**
	 * Provide text for list of items the team has
	 */
	private String teamItems;
	
	/**
	 * Provide text for list of abilities the team has
	 */
	private String teamAbilities;
	
	/**
	 * Provide text for list attributes associated with an item
	 */
	private String itemAttributes;
	
	/**
	 * text for Amount of money the team has
	 */
	 private String teamFunds;
	 
	 /**
	 * text for buying drinks
	 */
	 private String drinks;

		/*public Dialogue(String greeting, String farewell, String options, String purchase, String teamItems,
			String teamAbilities, String teamFunds) { */
	public Dialogue() { 
		greeting = "Welcome to my humble Inn. Make yourself at home can I get you a drink? ";
		farewell = "Farewell my friends. May your studies go well ";
		options = "You can buy drinks, power ups, healing item and maps. You can relax too."+ "\n"+ 
				"Would you like to ";
		purchase = "Itmes available for purcahse are: ";
		teamItems = "Your team items are: ";
		teamAbilities = "Your team abilites are: ";
		teamFunds = "Your team finances aren't looking too flash at: ";
		itemAttributes = "Items (if avilable) can do the following: ";
		vPlate = "Are you all students? Prove it, do you have a vPlate? ";
	}
	
	/**
	 * @return the greeting
	 */
	public String getGreeting() {
		return greeting;
	}
	
	/**
	 * @return the farewell
	 */
	public String getFarewell() {
		return farewell;
	}

	/**
	 * @return the options
	 */
	public String getOptions() {
		return options;
	}

	/**
	 * @return the purchase
	 */
	public String getPurchase() {
		return purchase;
	}

	/**
	 * @return the teamItems
	 */
	public String getTeamItems() {
		return teamItems;
	}

	/**
	 * @return the teamAbilities
	 */
	public String getTeamAbilities() {
		return teamAbilities;
	}

	/**
	 * @return the teamFunds
	 */
	public String getTeamFunds() {
		return teamFunds;
	}
	
	/**
	 * @return the item attributes
	 */
	public String getItemAttributes() {
		return itemAttributes;
	}
	
	/**
	 * @return the vplate message
	 */
	public String getVPlate() {
		return vPlate;
	}
	
}
