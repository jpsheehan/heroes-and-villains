package game.city;

public class TeamMovementException extends Exception {

	/**
	 * 
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
	 * Gets the direction the user intends to travel.
	 * @return
	 */
	public Direction getIntendedDirection() {
		
		return this.intendedDirection;
		
	}
	
}
