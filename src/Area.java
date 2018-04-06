
/**
 * Represents an Area within a City. A City has five Areas.
 *
 */
public abstract class Area implements Nameable {

	/**
	 * The name of the Area.
	 */
	private String name;
	
	/**
	 * The type of the Area.
	 */
	private AreaType type;
	
	/**
	 * Creates a new Area.
	 * @param name The name of the Area.
	 * @param type The type of the Area.
	 */
	public Area(String name, AreaType type) {
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Returns the name of the Area.
	 */
	public final String getName() {
		return this.name;
	}
	
	/**
	 * Returns the type of Area this represents.
	 * @return
	 */
	public final AreaType getType() {
		return this.type;
	}
	
}
