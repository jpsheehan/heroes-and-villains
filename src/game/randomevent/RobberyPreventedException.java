package game.randomevent;

import game.character.Hero;
import game.item.Item;

public class RobberyPreventedException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 2949632949303295648L;

	private Item itemNotRobbed;
	
	private Hero lawStudent;
	
	public RobberyPreventedException(Hero lawStudent, Item itemNotRobbed) {
		
		this.itemNotRobbed = itemNotRobbed;
		this.lawStudent = lawStudent;
		
	}
	
	public Item getItemNotRobbed() {
		
		return this.itemNotRobbed;
		
	}
	
	public Hero getLawStudent() {
		
		return this.lawStudent;
		
	}
}
