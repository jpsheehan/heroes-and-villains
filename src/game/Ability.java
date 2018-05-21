package game;

import java.io.Serializable;

/**
 * A placeholder interface that is parent to the ItemAbility and HeroAbility enumerations.
 * @author jesse
 *
 */
public interface Ability extends Serializable {
	
	/**
	 * Returns the ability as a string.
	 * @return A string.
	 */
	public String toString();
	
}
