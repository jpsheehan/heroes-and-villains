package game.item;

import java.util.ArrayList;
//import game.minigame.*;			//only needed for quick testing, to be removed later

//20180411
// initial build
//20180422
//convert back to 3 separate arraylists for powerups, healing, maps.
//	parse into correct list on addition of an item.
//		and return each separate list as a string on get
//		override toString to get the entire inventory

/**
 * The Inventory class holds lists of items for the teams and the shop as referenced in section 2.3.3 of the specification.
 *
 */
public class Inventory {
	
	/**
	 * Arraylist to hold objects of type PowerUpItem  
	 */
	private ArrayList<Item> listPowerups;
	
	/**
	 * Arraylist to hold objects of type Map 
	 */
	private ArrayList<Item> listMaps;
	
	/**
	 * Arraylist to hold objects of type HealingItem
	 */
	private ArrayList<Item> listHealingItems;
	
	
	/**
	 * Creates a new inventory, three ArrayLists
	 * No items in the lists on initialisation
	 */
	public Inventory() {
		listPowerups = new ArrayList<Item>();
		listMaps = new ArrayList<Item>();
		listHealingItems = new ArrayList<Item>();
	}
	
	/**
	 * Adds an item to the inventory
	 * Checks item type and adds to the appropriate list 
	 * @param Item
	 */
	public void addInventoryItem(Item item) {

		// check not null
		if (item == null) {
				throw new IllegalArgumentException("choice should not be null for an item to be added to the inventory.");
		}
		
		if (item instanceof HealingItem) {
			listHealingItems.add(item);
		}
		
		if (item instanceof Map) {
			listMaps.add(item);
		}
		
		if (item instanceof PowerUpItem) {
			listPowerups.add(item);
		}
				
		//System.out.println(item.getName()+ " added");		//only for testing
	}

	/**
	 * Removes an item from the inventory
	 * Checks item type, checks if already in appropriate list, if true removes from appropriate list
	 * @param Item
	 */
	public void removeInventoryItem(Item item) {
		boolean validItem = false;
		// check not null,
		if (item == null) {
			throw new IllegalArgumentException("choice should not be null for an item to be removed from the inventory.");
		}
		if (item instanceof HealingItem) {
			if (listHealingItems.contains(item)) {	//check if already in the list
				listHealingItems.remove(item);		// and if so remove
				//System.out.println("Item "+ item.getName()+ " removed");		//for testing
				validItem = true;
			}
		}
		if (item instanceof Map) {
			if (listMaps.contains(item)) {
				listMaps.remove(item);
				//System.out.println("Item "+ item.getName()+ " removed");
				validItem = true;
			}
		}
		
		if (item instanceof PowerUpItem) {
			if (listPowerups.contains(item)) {
				listPowerups.remove(item);
				//System.out.println("Item "+ item.getName()+ " removed");
				validItem = true;
			}
		}
		
		/*
		if (validItem == false) {										//for testing
			System.out.println("Item "+ item.getName()+ " not removed"); // return something else (e.g. boolean false and tell user elsewhere?)
		}
		*/
	}
	
	/**
	 * @return String containing list of Healing Items
	 * Formated to "Healing Items: itemName, itemName, "etc
	 */
	public String getHealingItems() {
		//Iterate through the Arraylist, identify item type, add to appropriate stringbuffer
		StringBuffer healingBuffer = new StringBuffer();
		for (int i = 0 ; i < listHealingItems.size(); i++) {	
			healingBuffer.append(listHealingItems.get(i).getName());
			healingBuffer.append(", ");
			}
		// if not empty remove the last comma and space
			if (healingBuffer.length() != 0) {
				healingBuffer.deleteCharAt(healingBuffer.length()-2);
			}
		return("Healing Items: "+ healingBuffer.toString());
	}
	
	/**
	 * @return String containing list of PowerUp Items
	 * Formated to "PowerUp Items: itemName, itemName, "etc
	 */
	public String getPowerUpItems() {
		StringBuffer powerUpBuffer = new StringBuffer();
		for (int i = 0 ; i < listPowerups.size(); i++) {	
			powerUpBuffer.append(listPowerups.get(i).getName());
			powerUpBuffer.append(", ");
			}
			if (powerUpBuffer.length() != 0) {
				powerUpBuffer.deleteCharAt(powerUpBuffer.length()-2);
			}
			return("PowerUp Items: "+ powerUpBuffer.toString());
	}
		
	/**
	 * return a String containing list of Map Item names
	 * Formated to "PowerUp Items: itemName, itemName, "etc
	 */
	public String getMapItems() {
		StringBuffer mapBuffer = new StringBuffer();		
		for (int i = 0 ; i < listMaps.size(); i++) {	
			mapBuffer.append(listMaps.get(i).getName());
			mapBuffer.append(", ");
			}
			if (mapBuffer.length() != 0) {
				mapBuffer.deleteCharAt(mapBuffer.length()-2);
			}
		return("Map Items: "+ mapBuffer.toString());
	}
	
	/**
	 * Returns the entire contents of the inventory.
	 * Separated by item type (Healing, Map, PowerUp
	 * @override
	 */
	public String toString() {
		return(getPowerUpItems()+ "\n"+ getMapItems()+ "\n"+ getHealingItems());
	}

	/* Only used for testing
	public static void main(String[] args) {
	
	Inventory teamInventory = new Inventory();
	
	//create some Maps
	Map engBuilding = new Map("Engineering City", "", 25);
	Map erskineBuilding = new Map("Computer Science City", "", 25);
	//Create some Healing items
	HealingItem health25 = new HealingItem("Health25%", "Heals 25% of your health", 2, 1, 5);
	HealingItem health50 = new HealingItem("Health50%", "Heals 50% of your health", 5, 2, 10);
	HealingItem health75 = new HealingItem("Health75%", "Heals 75% of your health", 10, 3, 20);
	//Create some power ups
	PowerUpItem winOnDraw = new PowerUpItem("Win on equal number", "Improved ondds", 10, ItemAbility.WIN_ON_DRAW, MinigameType.DICE_ROLLS);
	PowerUpItem fourGuesses = new PowerUpItem("More attemtps", "More chances", 10, ItemAbility.FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER, MinigameType.GUESS_THE_NUMBER);

	//1
	teamInventory.addInventoryItem(health25);
	teamInventory.addInventoryItem(engBuilding);
	teamInventory.addInventoryItem(winOnDraw);
	System.out.println(teamInventory);
	System.out.println("\n");
	//2
	teamInventory.addInventoryItem(erskineBuilding);
	teamInventory.addInventoryItem(health50);
	teamInventory.addInventoryItem(fourGuesses);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	//3
	teamInventory.removeInventoryItem(health75);
	teamInventory.addInventoryItem(health75);
	teamInventory.removeInventoryItem(fourGuesses);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	teamInventory.removeInventoryItem(health75);
	teamInventory.removeInventoryItem(fourGuesses);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	teamInventory.removeInventoryItem(erskineBuilding);
	System.out.println(teamInventory.getHealingItems());
	System.out.println(teamInventory.getMapItems());
	System.out.println(teamInventory.getPowerUpItems());
	}
	*/
}
