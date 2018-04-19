package game.city;

import static game.GeneralHelpers.getString;

public enum CityType {
	
	JAMES_HIGHT,
	RUTHERFORD,
	ERSKINE,
	ENG_CORE,
	PSYCH,
	LAW,
	MATARIKI;
	
	@Override
	public String toString() {
		
		switch (this) {
		
			case ENG_CORE:
				return getString("EngCore.Name");
				
			case ERSKINE:
				return getString("Erskine.Name");
				
			case JAMES_HIGHT:
				return getString("JamesHight.Name");
				
			case LAW:
				return getString("Law.Name");
				
			case MATARIKI:
				return getString("Matariki.Name");
				
			case PSYCH:
				return getString("Psych.Name");
				
			case RUTHERFORD:
				return getString("Rutherford.Name");
			
			default:
				return "null";
		
		}
		
	}

}
