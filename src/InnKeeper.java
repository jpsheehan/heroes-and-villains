
public class InnKeeper extends Character {
	
	/**
	 * The dialogue the InnKeeper will use.
	 */
	private ShopDialogue dialogue;
	
	/**
	 * Create's a new InnKeeper with a name and a ShopDialogue.
	 * @param name The name of the InnKeeper.
	 * @param dialogue The dialogue that the InnKeeper will use.
	 */
	public InnKeeper(String name, ShopDialogue dialogue) {
		super(name);
		
		this.dialogue = dialogue;
	}
	
	/**
	 * Returns the ShopDialogue of the InnKeeper
	 */
	public ShopDialogue getDialogue() {
		return this.dialogue;
	}

}
