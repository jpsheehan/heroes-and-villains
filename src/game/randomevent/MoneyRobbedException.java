package game.randomevent;

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
