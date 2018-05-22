package game.city;

public class IllegalMoveException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6113836583855376653L;
	
	/**
	 * The current absolute location of the team.
	 */
	private Direction currentDirection;
	
	/**
	 * The relative direction the team tried to move in.
	 */
	private Direction failedDirection;
	
	/**
	 * Creates a new IllegalMoveException instance.
	 * @param currentDirection The current absolute location of the team. 
	 * @param failedDirection The relative direction the team tried to move to.
	 */
	public IllegalMoveException(Direction currentDirection, Direction failedDirection) {
		
		super(String.format("Cannot move from %s to %s.", currentDirection.toString(), failedDirection.toString()));
		
		this.currentDirection = currentDirection;
		this.failedDirection = failedDirection;
		
	}
	
	/**
	 * @return The absolute location of the team.
	 */
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
	
	/**
	 * @return The relative direction the team tried to move in.
	 */
	public Direction getFailedDirection() {
		return this.failedDirection;
	}

}
