package game.character;

public class InnKeeper extends Character {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -7255001793893507756L;
	/**
	 * The dialogue the InnKeeper will use.
	 */
	private Dialogue dialogue;
	
	/**
	 * Creates a new InnKeeper with a name and a ShopDialogue.
	 * @param name The name of the InnKeeper.
	 * @param dialogue The dialogue that the InnKeeper will use.
	 */
	public InnKeeper(String name, Dialogue dialogue) {
		super(name);
		
		this.dialogue = dialogue;
	}
	
	/**
	 * Returns the ShopDialogue of the InnKeeper
	 */
	public Dialogue getDialogue() {
		return this.dialogue;
	}

}
