package game.randomevent;

import game.item.Item;

/**
 * Represents the random event that a Team receives a gift per Section 2.3 of the specification
 * @author jesse
 */
public class ItemGiftedException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 3016232556558026678L;
	
	private Item itemGifted;
	
	public ItemGiftedException(Item itemGifted) {
		
		this.itemGifted = itemGifted;
		
	}
	
	public Item getItemGifted() {
		
		return this.itemGifted;
		
	}

}
