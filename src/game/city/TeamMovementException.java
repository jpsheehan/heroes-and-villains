package game.city;

/**
 * Represents a failure of the team moving.
 * @author jesse
 */
public class TeamMovementException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -8760264434797753277L;

	/**
	 * The direction the user intends to travel.
	 */
	private Direction intendedDirection;
	
	/**
	 * Creates a new TeamMovementException.
	 * @param intendedDirection The direction the user intends to travel.
	 */
	public TeamMovementException(Direction intendedDirection) {
		
		this.intendedDirection = intendedDirection;
		
	}
	
	/**
	 * @return The direction the user intends to travel.
	 */
	public Direction getIntendedDirection() {
		
		return this.intendedDirection;
		
	}
	
}
