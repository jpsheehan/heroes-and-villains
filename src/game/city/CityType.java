package game.city;

import java.io.Serializable;

import game.GeneralHelpers;

/**
 * The type of cities that exist in the game
 * @author jesse
 */
public enum CityType implements Serializable {
	
	JAMES_HIGHT,
	RUTHERFORD,
	ERSKINE,
	ENG_CORE,
	PSYCH,
	LAW,
	MATARIKI;
	
	/**
	 * @return The name of the city as it appears in the strings file.
	 */
	public String getProperName() {
		
		switch (this) {
		
			case ENG_CORE:
				return "EngCore";
				
			case ERSKINE:
				return "Erskine";
				
			case JAMES_HIGHT:
				return "JamesHight";
				
			case LAW:
				return "Law";
				
			case MATARIKI:
				return "Matariki";
				
			case PSYCH:
				return "Psych";
				
			case RUTHERFORD:
				return "Rutherford";
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
	/**
	 * @return The human-readable version of the city type.
	 */
	public String getName() {
		
		return GeneralHelpers.getString(this.getProperName() + ".Name");
		
	}

}
