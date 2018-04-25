package game.city;

/**
 * Denotes the type of area an Area object is.
 *
 */
public enum AreaType {
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
				return "null";
		}
		
	}
}
