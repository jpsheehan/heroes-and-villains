package game.item;

import java.util.ArrayList;

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
	
	//Three separate lists for each specific item. (May change to one list for all items?)
	/**
	 * List of PowerUps
	 */
	//private ArrayList<Item> listPowerups;
	
	/**
	 * list of maps
	 */
	//private ArrayList<Item> listMaps;

	/**
	 * List of healing items
	 */
	//private ArrayList<Item> listHealingItems;

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
	 * Returns the items in the list
	 * @param String identifies which list
	 * @return all items in the list
	 */
	public String getInventoryList(String whichList) { //maybe toString override might be better
		return "List";
	}
	
	/*
	 * @override
	 */
	public String toString() {
		StringBuffer listBuffer = new StringBuffer();
		for (int i = 0; i < inventoryList.size(); i++) {
			listBuffer.append(inventoryList.get(i).getName());
			listBuffer.append(", ");
		}
		return(listBuffer.toString());
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
		else System.out.println("Item not removed"); // return something else (e.g. boolean false and tell user elsewhere?)
	}

	public static void main(String[] args) {
	
	Inventory teamInventory = new Inventory();
	
	Map engBuilding = new Map("Engineering City", 25);
	Map erskineBuilding = new Map("Computer Science City", 25);
	HealingItem health25 = new HealingItem("Health25%", 2, 1, 5);
	HealingItem health50 = new HealingItem("Health50%", 5, 2, 10);
	HealingItem health75 = new HealingItem("Health75%", 10, 3, 20);
	
	teamInventory.addInventoryItem(health25);
	teamInventory.addInventoryItem(engBuilding);
	teamInventory.addInventoryItem(erskineBuilding);
	teamInventory.addInventoryItem(health50);
	System.out.println(teamInventory);
	
	teamInventory.removeInventoryItem(health75);
	teamInventory.addInventoryItem(health75);

	System.out.println(teamInventory);
	}
}
	

