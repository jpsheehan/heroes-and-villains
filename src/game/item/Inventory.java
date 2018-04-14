package game.item;

import java.util.ArrayList;

//import game.minigame.*;

//20180411

public class Inventory {

/**
 * The Inventory class holds lists of items for the teams and the shop as referenced in section 2.3.3 of the specification.
 *
 */
	/**
	 * Arraylist to hold objects of type Item (HealingItem, Map, PowerUps, )  
	 */
	private ArrayList<Item> inventoryList;
	
	/**
	 * Creates a new inventory.
	 * No items in the list(s)at initialisation
	 */
	public Inventory() {
		inventoryList = new ArrayList<Item>();
		//listPowerups = new ArrayList<Item>( );
		//listMaps = new ArrayList<Item>( );
		//listHealingItems = new ArrayList<Item>( );
	}
	
	/**
	 * Adds an item to the inventory
	 * @param Item
	 */
	public void addInventoryItem(Item Item) {

	//public void addInventoryItem(Item Item, String whichList) {
		// check not null, add to list
		if (Item == null) {
				throw new IllegalArgumentException("choice should be null for an item to be added to the inventory.");
		}
		inventoryList.add(Item);
		System.out.println(Item.getName()+ " added");
	}

	/**
	 * Removes an item from the inventory
	 * @param Item
	 */
	public void removeInventoryItem(Item Item) {
		// check not null, 
		if (Item == null) {
			throw new IllegalArgumentException("choice should be null for an item to be removed from the inventory.");
		}
		if (inventoryList.contains(Item)) {			//check if already in the list
			inventoryList.remove(Item);				// and if so remove
		}
		else System.out.println("Item"+ Item.getName()+ " not removed"); // return something else (e.g. boolean false and tell user elsewhere?)
	}
	
	/**
	 * return a String containing list of Healing Items 
	 */
	public String getHealingItems() {
		//Iterate through the Arraylist, identify item type, add to appropriate stringbuffer
		StringBuffer healingBuffer = new StringBuffer();
		for (int i = 0 ; i < inventoryList.size(); i++) {	
			if (inventoryList.get(i) instanceof HealingItem) {
				healingBuffer.append(inventoryList.get(i).getName());
				healingBuffer.append(", ");
				}
			}
		// if not empty remove the last comma and space
			if (healingBuffer.length() != 0) {
				healingBuffer.deleteCharAt(healingBuffer.length()-2);
			}
		return("Healing Items: "+ healingBuffer.toString());
	}
	
	/**
	 * return a String containing list of PowerUp Item names 
	 */
	public String getPowerUpItems() {
		StringBuffer powerUpBuffer = new StringBuffer();
		for (int i = 0 ; i < inventoryList.size(); i++) {	
			if (inventoryList.get(i) instanceof PowerUpItem) {
				powerUpBuffer.append(inventoryList.get(i).getName());
				powerUpBuffer.append(", ");
				}
			}
			if (powerUpBuffer.length() != 0) {
				powerUpBuffer.deleteCharAt(powerUpBuffer.length()-2);
			}
			return("PowerUp Items: "+ powerUpBuffer.toString());
	}
	
	
	/**
	 * return a String containing list of Map Item names 
	 */
	public String getMapItems() {
		StringBuffer mapBuffer = new StringBuffer();		
		for (int i = 0 ; i < inventoryList.size(); i++) {	
			if (inventoryList.get(i) instanceof Map) {
				mapBuffer.append(inventoryList.get(i).getName());
				mapBuffer.append(", ");
				}
			}
			if (mapBuffer.length() != 0) {
				mapBuffer.deleteCharAt(mapBuffer.length()-2);
			}
		return("Map Items: "+ mapBuffer.toString());
	}
	
	/**
	 * Returns the contents of the inventory.
	 * Separated by item type (Healing, Map, PowerUp
	 * @override
	 */
	public String toString() {
		return(getPowerUpItems()+ "\n"+ getMapItems()+ "\n"+ getHealingItems());
	}

	public static void main(String[] args) {
	
	Inventory teamInventory = new Inventory();
	
	//create some Maps
	Map engBuilding = new Map("Engineering City", 25);
	Map erskineBuilding = new Map("Computer Science City", 25);
	//Create some Healing items
	HealingItem health25 = new HealingItem("Health25%", 2, 1, 5);
	HealingItem health50 = new HealingItem("Health50%", 5, 2, 10);
	HealingItem health75 = new HealingItem("Health75%", 10, 3, 20);
	//Create some power ups
	//PowerUpItem alwaysRollSix = new PowerUpItem("Roll 6", 10, "Always Roll 6", DiceRolls);

	teamInventory.addInventoryItem(health25);
	teamInventory.addInventoryItem(engBuilding);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	teamInventory.addInventoryItem(erskineBuilding);
	teamInventory.addInventoryItem(health50);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	teamInventory.removeInventoryItem(health75);
	teamInventory.addInventoryItem(health75);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	teamInventory.removeInventoryItem(health75);
	System.out.println(teamInventory);
	System.out.println("\n");
	
	System.out.println(teamInventory.getHealingItems());
	System.out.println(teamInventory.getMapItems());
	System.out.println(teamInventory.getPowerUpItems());
	}
}
	

