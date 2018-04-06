package game;

public class TeamFullException extends Exception {

	/**
	 * Dunno what this thing is. But apparently we need it.
	 */
	private static final long serialVersionUID = -6758941000725936480L;
	
	public TeamFullException(String message) {
		super(message);
	}
}
