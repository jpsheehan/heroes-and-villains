package game.city;

/**
 * Represents the Hospital as described on section 2.3.4 of the specification.
 *
 */
public class Hospital extends Area {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6474105888220400469L;

	/**
	 * Creates a new Hospital.
	 * @param name The name of the Hospital.
	 * @param description The information that relates to the Hospital
	 */
	public Hospital(String name, String description) {
		super(name, description, AreaType.HOSPITAL);
	}

}
