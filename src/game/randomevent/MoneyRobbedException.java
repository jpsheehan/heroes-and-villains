package game.randomevent;

/**
 * Represents the random event that a Team gets money robbed per Section 2.3 of the specification
 * @author jesse
 */
public class MoneyRobbedException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -1186331298463841504L;

	private int moneyRobbed;
	
	public MoneyRobbedException(int moneyRobbed) {
		
		this.moneyRobbed = moneyRobbed;
		
	}
	
	public int getMoneyRobbed() {
		
		return this.moneyRobbed;
		
	}
}
