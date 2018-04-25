package game.city;

/**
 * Represents the Hospital as described on section 2.3.4 of the specification.
 *
 */
public class Hospital extends Area {
	
	/**
	 * Creates a new Hospital.
	 * @param name The name of the Hospital.
	 */
	public Hospital(String name, String description) {
		super(name, description, AreaType.HOSPITAL);
	}

}
