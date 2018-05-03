package game.item;

import java.util.ArrayList;

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
			
			throw new NullPointerException("item cannot be null");
			
		}
		
		if (this.exists(item)) {
			
			throw new IllegalArgumentException(String.format("item %s already exists in inventory", item.getName()));
			
		}
		
		if (item instanceof HealingItem) {
			
			listHealingItems.add(item);
			return;
			
		}
		
		if (item instanceof Map) {
			
			listMaps.add(item);
			return;
		}
		
		if (item instanceof PowerUpItem) {
			
			listPowerUpItems.add(item);
			return;
			
		}
		
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
	
	/**
	 * Returns an array of maps in the inventory.
	 * @return
	 */
	public Map[] getMaps() {

		Map[] array = new Map[this.listMaps.size()];
		array = this.listMaps.toArray(array);
		return array;
		
	}
	
	/**
	 * Returns an array of power up items in the inventory.
	 * @return
	 */
	public PowerUpItem[] getPowerUpItems() {
		
		PowerUpItem[] array = new PowerUpItem[this.listPowerUpItems.size()];
		array = this.listPowerUpItems.toArray(array);
		return array;
		
	}
	
	/**
	 * Returns an array of healing items in the inventory.
	 * @return
	 */
	public HealingItem[] getHealingItems() {

		HealingItem[] array = new HealingItem[this.listHealingItems.size()];
		array = this.listHealingItems.toArray(array);
		return array;
		
	}
	
	/**
	 * Returns the number of items in the inventory.
	 * @return
	 */
	public int size() {
		
		return this.listHealingItems.size() + this.listMaps.size() + this.listPowerUpItems.size();
		
	}
}
