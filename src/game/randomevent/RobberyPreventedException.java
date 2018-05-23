package game.randomevent;

import game.item.Item;

public class RobberyPreventedException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 2949632949303295648L;

	private Item itemNotRobbed;
	
	public RobberyPreventedException(Item itemNotRobbed) {
		
		this.itemNotRobbed = itemNotRobbed;
		
	}
	
	public Item getItemNotRobbed() {
		
		return this.itemNotRobbed;
		
	}
}
