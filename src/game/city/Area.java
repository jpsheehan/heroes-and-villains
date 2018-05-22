package game.city;

import java.io.Serializable;

import game.Describable;
import game.Nameable;

/**
 * Represents an Area within a City. A City has five Areas.
 *
 */
public abstract class Area implements Nameable, Describable, Serializable {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 8493046604045045279L;

	/**
	 * The name of the Area.
	 */
	private String name;
	
	/**
	 * The type of the Area.
	 */
	private AreaType type;
	
	/**
	 * The description of the Area.
	 */
	private String description;
	
	/**
	 * Creates a new Area.
	 * @param name The name of the Area.
	 * @param description The description of the Area.
	 * @param type The type of the Area.
	 */
	public Area(String name, String description, AreaType type) {
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	/**
	 * @return The name of the Area.
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * @return The type of Area this represents.
	 */
	public final AreaType getType() {
		return this.type;
	}
	
	/**
	 * @return The description of the Area.
	 */
	public final String getFlavourText() {
		return this.description;
	}
	
}
