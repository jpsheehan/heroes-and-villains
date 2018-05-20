package game.city;

/**
 * Represents the Hospital as described on section 2.3.4 of the specification.
 *
 */
public class Hospital extends Area {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6474105888220400469L;

	/**
	 * Creates a new Hospital.
	 * @param name The name of the Hospital.
	 */
	public Hospital(String name, String description) {
		super(name, description, AreaType.HOSPITAL);
	}

}
