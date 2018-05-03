package game.city;

import game.GeneralHelpers;

public enum CityType {
	
	JAMES_HIGHT,
	RUTHERFORD,
	ERSKINE,
	ENG_CORE,
	PSYCH,
	LAW,
	MATARIKI;
	
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
	
	public String getName() {
		
		return GeneralHelpers.getString(this.getProperName() + ".Name");
		
	}

}
