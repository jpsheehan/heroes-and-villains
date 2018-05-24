package game.randomevent;

import game.item.Item;

/**
 * Represents the random event that a Team gets an Item robbed per Section 2.3 of the specification
 * @author jesse
 */
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
