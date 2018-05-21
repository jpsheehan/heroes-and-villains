package game.item;

import java.io.Serializable;

/**
 * Represents a Map as described in section 2.3 of the specification.
 *
 */
public class Map extends Item implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 1682836575817898404L;

	/**
	 * Creates a new Map.
	 * @param name The name of the Map.
	 * @param price The price of the Map.
	 */
	public Map(String name, String flavourText, int price) {
		super(name, flavourText, price, ItemType.MAP);
	}
	
}
