package game.item;

/**
 * Denotes the type of item an Item object is.
 */
import java.io.Serializable;

public enum ItemType implements Serializable {
	MAP,
	HEALING_ITEM,
	POWER_UP_ITEM;
	
	@Override
	public String toString() {
		
		switch (this) {
			
			case MAP:
				return "Map";
				
			case HEALING_ITEM:
				return "Healing Item";
				
			case POWER_UP_ITEM:
				return "Power Up Item";
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
}
