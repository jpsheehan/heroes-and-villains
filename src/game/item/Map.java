package game.item;

/**
 * Represents a Map as described in section 2.3 of the specification.
 *
 */
public class Map extends Item {
	
	/**
	 * Creates a new Map.
	 * @param name The name of the Map.
	 * @param price The price of the Map.
	 */
	public Map(String name, String flavourText, Integer price) {
		super(name, flavourText, price);
	}
	
}
