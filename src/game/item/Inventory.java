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
	 * ArrayList to hold objects of type PowerUpItem  
	 */
	private ArrayList<Item> listPowerUpItems;
	
	/**
	 * ArrayList to hold objects of type Map 
	 */
	private ArrayList<Item> listMaps;
	
	/**
	 * ArrayList to hold objects of type HealingItem
	 */
	private ArrayList<Item> listHealingItems;
	
	
	/**
	 * Creates a new inventory, three ArrayLists
	 * No items in the lists on initialization
	 */
	public Inventory() {
		
		listPowerUpItems = new ArrayList<Item>();
		listMaps = new ArrayList<Item>();
		listHealingItems = new ArrayList<Item>();
		
	}
	
	/**
	 * Adds an item to the inventory. 
	 * @param Item The item to add.
	 */
	public void add(Item item) {

		// check not null
		if (item == null) {
			
			throw new IllegalArgumentException("item cannot be null");
			
		}
		
		if (item instanceof HealingItem) {
			
			listHealingItems.add(item);
			
		}
		
		if (item instanceof Map) {
			
			listMaps.add(item);
			
		}
		
		if (item instanceof PowerUpItem) {
			
			listPowerUpItems.add(item);
			
		}
		
		// Item is not a Map, PowerUpItem or HealingItem.
		throw new AssertionError();
	}

	/**
	 * Removes an item from the inventory.
	 * @param Item The item to remove.
	 */
	public void remove(Item item) {
		
		// The exception to throw in case the item is not found.
		IllegalArgumentException itemNotFound = new IllegalArgumentException(
				String.format("item %s not found in inventory", item.getName()));
		
		// remove item from healing item list
		if (item instanceof HealingItem) {
			
			if (!listHealingItems.remove(item)) {
				
				throw itemNotFound;
				
			} else {
				
				return;
				
			}
			
		}
		
		// remove item from map list
		if (item instanceof Map) {
			
			if (!listMaps.remove(item)) {
				
				throw itemNotFound;
				
			} else {
				
				return;
				
			}
			
		}
		
		// remove item from power up item
		if (item instanceof PowerUpItem) {
			
			if (!listPowerUpItems.remove(item)) {
				
				throw itemNotFound;
				
			} else {
				
				return;
				
			}
			
		}
		
		// the item is not a Map, PowerUpItem or HealingItem
		throw new AssertionError();
		
	}
	
	/**
	 * Checks to see if the item exists.
	 * @param item The item to check for.
	 * @return
	 */
	public boolean exists(Item item) {
		
		// check if item is a healing item
		if (item instanceof HealingItem && listHealingItems.contains(item)) {
			
			return true;
			
		}

		// check if item is a map
		if (item instanceof Map && listMaps.contains(item)) {
			
			return true;
			
		}

		// check if item is a power up item
		if (item instanceof PowerUpItem && listPowerUpItems.contains(item)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
}
