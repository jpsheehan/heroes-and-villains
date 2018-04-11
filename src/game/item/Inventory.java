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
	private ArrayList<PowerUpItem> listPowerups;
	
	/**
	 * list of maps
	 */
	private ArrayList<Map> listMaps;

	/**
	 * List of healing items
	 */
	private ArrayList<HealingItem> listHealingItems;

	/**
	 * Creates a new inventory.
	 * No items in the list(s)at initialisation
	 */
	public Inventory() {
		listPowerups = new ArrayList<PowerUpItem>( );
		listMaps = new ArrayList<Map>( );
		listHealingItems = new ArrayList<HealingItem>( );
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
	public void addInventoryItem(String Item, String whichList) {
		// check not null, add to list
		//
	}
	/**
	 * Removes an item from the inventory
	 * @param Item, whichList
	 */
	public void removeInventoryItem(String Item) {
		// check not null, iterate through array to find item index, 
		// a check and respond if not in the list, then remove item.
		//
	}
	
}