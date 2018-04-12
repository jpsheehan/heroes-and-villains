package game.item;

import java.util.ArrayList;

//20180411

public class Inventory {

/**
 * The Inventory class holds lists of items for the teams and the shop as referenced in section 2.3.3 of the specification.
 *
 */
	
	//Three separate lists for each specific item. (May change to one list for all items?)
	/**
	 * List of PowerUps
	 */
	private ArrayList<Item> listPowerups;
	
	/**
	 * list of maps
	 */
	private ArrayList<Item> listMaps;

	/**
	 * List of healing items
	 */
	private ArrayList<Item> listHealingItems;

	/**
	 * Creates a new inventory.
	 * No items in the list(s)at initialisation
	 */
	public Inventory() {
		listPowerups = new ArrayList<Item>( );
		listMaps = new ArrayList<Item>( );
		listHealingItems = new ArrayList<Item>( );
	}
	
	/**
	 * Returns the items in the list
	 * @param String identifies which list
	 * @return all items in the list
	 */
	public String getInventoryList(String whichList) { //maybe toString override might be better
		return "List";
	}
	
	/**
	 * Adds an item to the inventory
	 * @param Item, whichList
	 */
	public void addInventoryItem(Item Item, String whichList) {
		// check not null, add to list
		if (Item == null) {
				throw new IllegalArgumentException("choice should be null for an item to be added to the inventory.");
		}
		switch(whichList) {
			case "PowerUpItems" :
				listPowerups.add(Item);
				break;
			case "Healingitem" :
				listHealingItems.add(Item);
				break;
			case "Map" :
				listMaps.add(Item);
				break;
			default :
				System.out.println("Item not added");
		}
	}

	/**
	 * Removes an item from the inventory
	 * @param Item, whichList
	 */
	public void removeInventoryItem(Item Item, String whichList) {
		// check not null, 
		if (Item == null) {
			throw new IllegalArgumentException("choice should be null for an item to be removed from the inventory.");
		}
		//check which list the item belongs in
		switch(whichList) {
			case "PowerUpItems" :
				if (listPowerups.contains(Item)) {			//check if already in the list
					listPowerups.remove(Item);
					}
				break;
			case "Healingitem" :
				if (listHealingItems.contains(Item)) {
					listHealingItems.remove(Item);
				}
				break;
			case "Map" :
				if (listMaps.contains(Item)) {
					listMaps.remove(Item);
				}
				break;
			default :
				System.out.println("Item not removed");
		}
	}
	
}