package game.randomevent;

import game.character.Hero;
import game.item.Item;

/**
 * Prevents a team from being robbed if there is a Law Student in the team
 * @author jesse
 */
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
