package game.item;

import game.Describable;
import game.Nameable;

public abstract class Item implements Buyable, Nameable, Describable {
	
	/**
	 * The name of the Item.
	 */
	private String name;
	
	/**
	 * The price of the Item.
	 */
	private Integer price;
	
	/**
	 * The description of the item.
	 */
	private String flavourText;
	
	/**
	 * Creates a new Item.
	 * @param name The name of the Item.
	 * @param price The price of the Item.
	 */
	public Item(String name, String flavourText, Integer price) {
		this.name = name;
		this.flavourText = flavourText;
		this.price = price;
	}

	/**
	 * Returns the name of the Item.
	 */
	@Override
	public final String getName() {
		return this.name;
	}

	/**
	 * Returns the price of the Item.
	 */
	@Override
	public final Integer getPrice() {
		return this.price;
	}
	
	/**
	 * Returns the flavour text describing this item.
	 */
	@Override
	public final String getFlavourText() {
		return this.flavourText;
	}

}
