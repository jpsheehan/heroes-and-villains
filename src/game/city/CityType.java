package game.city;

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
				return "null";
		
		}
		
	}

}
