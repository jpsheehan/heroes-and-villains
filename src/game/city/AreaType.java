package game.city;

import java.io.Serializable;

/**
 * Denotes the type of area an Area object is.
 */
public enum AreaType implements Serializable {
	
	/**
	 * Where the team can use a map.
	 */
	HOME_BASE,
	
	/**
	 * Where the team can buy items.
	 */
	SHOP,
	
	/**
	 * Where the team can battle the villain.
	 */
	VILLAINS_LAIR,
	
	/**
	 * Where the team can apply healing items.
	 */
	HOSPITAL,
	
	/**
	 * Where the team can apply power up items.
	 */
	POWER_UP_DEN;
	
	/**
	 * @return A human readable string denoting the type of area.
	 */
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
	 * @return The AreaType as a string that can be used as a specifier in strings.json.
	 */
	public String toProperString() {
		
		// Kind of lazy implementation lol
		return this.toString().replaceAll(" ", "").replaceAll("'", "");
		
	}
	
	/**
	 * @return The letter to use as a legend on a map.
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
