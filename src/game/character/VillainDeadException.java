package game.character;

/**
 * The exception to be thrown when the Villain dies.
 * @author jesse
 *
 */
public class VillainDeadException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6584038847570515698L;
	
	/**
	 * The villain that has died.
	 */
	private Villain villain;
	
	/**
	 * The money reward for defeating the villain.
	 */
	private int reward;
	
	/**
	 * Creates a new VillainDeadException.
	 * @param villain The villain that has died.
	 */
	public VillainDeadException(Villain villain, int reward) {
		
		super(String.format("The villain '%s' has died.", villain.toString()));
		
		this.villain = villain;
		this.reward = reward;
		
	}
	
	/**
	 * @return The Villain that has died.
	 */
	public Villain getVillain() {
		
		return this.villain;
		
	}
	
	/**
	 * @return The reward for defeating the Villain.
	 */
	public int getReward() {
		
		return this.reward;
		
	}
}
