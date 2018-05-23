package game.randomevent;

import java.io.Serializable;

/**
 * Denotes a kind of random event that can occur when the Team visits a City.
 * @author jesse
 *
 */
public enum RandomEventType implements Serializable {

	/**
	 * The team gets robbed of an item.
	 */
	ROBBERY,
	
	/**
	 * The team is gifted an item.
	 */
	GIFT;
	
}
