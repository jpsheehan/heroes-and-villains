package game.character;

import java.io.Serializable;

import game.Nameable;

/**
 * Represents any character in the game.
 *
 */
public abstract class Character implements Nameable, Serializable {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 8369375027658819446L;
	
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
