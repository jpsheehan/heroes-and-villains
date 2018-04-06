package game;

public abstract class Item implements Buyable, Nameable {
	
	/**
	 * The name of the Item.
	 */
	private String name;
	
	/**
	 * The price of the Item.
	 */
	private Integer price;
	
	/**
	 * Creates a new Item.
	 * @param name The name of the Item.
	 * @param price The price of the Item.
	 */
	public Item(String name, Integer price) {
		this.name = name;
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

}
