package game.city;

/**
 * Represents a Power Up Den.
 * @author jesse
 */
public class PowerUpDen extends Area {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 2322987305678031051L;

	/**
	 * Creates a new Power Up Den.
	 * @param name The name of the area.
	 */
	public PowerUpDen(String name, String description) {
		super(name, description, AreaType.POWER_UP_DEN);
	}

}
