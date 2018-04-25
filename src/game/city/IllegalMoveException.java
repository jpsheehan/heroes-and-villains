package game.city;

public class IllegalMoveException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6113836583855376653L;
	
	private Direction currentDirection;
	
	private Direction failedDirection;
	
	public IllegalMoveException(Direction currentDirection, Direction failedDirection) {
		
		super(String.format("Cannot move from %s to %s.", currentDirection.toString(), failedDirection.toString()));
		
		this.currentDirection = currentDirection;
		this.failedDirection = failedDirection;
		
	}
	
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}
	
	public Direction getFailedDirection() {
		return this.failedDirection;
	}

}
