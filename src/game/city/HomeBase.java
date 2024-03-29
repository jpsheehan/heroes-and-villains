package game.city;

/**
 * Represents a HomeBase.
 * @author jesse
 */
public class HomeBase extends Area {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 3778410241998997381L;

	/**
	 * Creates a new Home Base.
	 * @param name The name of the Home Base.
	 * @param description The information that relates to the HomeBase
	 */
	public HomeBase(String name, String description) {
		super(name, description, AreaType.HOME_BASE);
	}

}
