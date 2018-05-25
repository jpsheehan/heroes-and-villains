package game.city;

import java.util.ArrayList;

import game.character.InnKeeper;
import game.item.Inventory;
import game.item.Item;

/**
 * The Shop class as described in section 2.3.2 of the specification.
 */
public class Shop extends Area {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -1022651346182238023L;

	/**
	 * The list of Items the shop sells.
	 */
	private Inventory inventory;
	
	/**
	 * The InnKeeper this shop has. Every shop must have an InnKeeper.
	 */
	private InnKeeper innKeeper;
	
	/**
	 * Creates a new Shop.
	 * @param name The name of the Shop.
	 * @param items The items the Shop sells.
	 * @param innKeeper The InnKeeper sells the Items.
	 * @param description The information that relates to the Shop
	 */
	public Shop(String name, String description, ArrayList<Item> items, InnKeeper innKeeper) {
		super(name, description, AreaType.SHOP);
		
		this.inventory = new Inventory();
		
		for (Item item : items) {
			
			this.inventory.add(item);
			
		}
		
		this.innKeeper = innKeeper;
	}
	
	/**
	 * @return The list of Items to sell.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * @return The InnKeeper.
	 */
	public InnKeeper getInnKeeper() {
		return this.innKeeper;
	}

}
