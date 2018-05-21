package game;

public class TeamFullException extends Exception {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -6758941000725936480L;
	
	/**
	 * Creates an instance of TeamFullException.
	 * @param message The message to pass to the exception.
	 */
	public TeamFullException(String message) {
		super(message);
	}
}
