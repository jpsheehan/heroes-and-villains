package game.city;

import java.io.Serializable;

/**
 * Denotes the type of area an Area object is.
 *
 */
public enum AreaType implements Serializable {
	HOME_BASE,
	SHOP,
	VILLAINS_LAIR,
	HOSPITAL,
	POWER_UP_DEN;
	
	@Override
	public String toString() {
		
		switch (this) {
		
			case HOME_BASE:
				return "Home Base";
				
			case HOSPITAL:
				return "Hospital";
				
			case POWER_UP_DEN:
				return "Power Up Den";
				
			case SHOP:
				return "Shop";
				
			case VILLAINS_LAIR:
				return "Villain's Lair";
			
			default:
				throw new AssertionError();
		}
		
	}
	
	/**
	 * Gets the AreaType as a string that can be used as a specifier in strings.json.
	 * @return
	 */
	public String toProperString() {
		
		return this.toString().replaceAll(" ", "").replaceAll("'", "");
		
	}
	
	/**
	 * Returns the letter to use as a legend on a map.
	 * @return
	 */
	public String getMapLegend() {
		
		switch (this) {
			
			case HOME_BASE:
				return "B";
				
			case HOSPITAL:
				return "H";
				
			case POWER_UP_DEN:
				return "P";
				
			case SHOP:
				return "S";
				
			case VILLAINS_LAIR:
				return "V";
				
			default:
				throw new AssertionError();
		
		}
		
	}
}
