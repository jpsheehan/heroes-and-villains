package game;

/**
 * Represents any character in the game.
 *
 */
public abstract class Character implements Nameable {

	/**
	 * The name of the Character.
	 */
	private String name;
	
	/**
	 * Creates a new Character.
	 * @param name The name of the Character.
	 */
	public Character(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the Character.
	 */
	public final String getName() {
		return this.name;
	}
}
