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
	 * Gets the Villain that has died.
	 * @return
	 */
	public Villain getVillain() {
		
		return this.villain;
		
	}
	
	/**
	 * Gets the reward for defeating the Villain.
	 * @return
	 */
	public int getReward() {
		
		return this.reward;
		
	}
}
