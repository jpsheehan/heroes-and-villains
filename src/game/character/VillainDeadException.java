package game.character;

/**
 * The exception to be thrown when the Villain dies.
 * @author jesse
 *
 */
public class VillainDeadException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6584038847570515698L;
	
	/**
	 * The villain that has died.
	 */
	private Villain villain;
	
	/**
	 * Creates a new VillainDeadException.
	 * @param villain The villain that has died.
	 */
	public VillainDeadException(Villain villain) {
		
		super(String.format("The villain '%s' has died.", villain.toString()));
		
		this.villain = villain;
		
	}
	
	/**
	 * Gets the Villain that has died.
	 * @return
	 */
	public Villain getVillain() {
		
		return this.villain;
		
	}
}
