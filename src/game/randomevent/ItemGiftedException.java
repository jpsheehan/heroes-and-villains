package game.randomevent;

import game.item.Item;

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
