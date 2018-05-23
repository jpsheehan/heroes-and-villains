package game.randomevent;

import game.item.Item;

public class ItemRobbedException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -906166925962147415L;

	private Item itemRobbed;
	
	public ItemRobbedException(Item itemRobbed) {
		
		this.itemRobbed = itemRobbed;
		
	}
	
	public Item getItemRobbed() {
		return this.itemRobbed;
	}
}
