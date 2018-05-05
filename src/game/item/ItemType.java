package game.item;

public enum ItemType {
	MAP,
	HEALING_ITEM,
	POWER_UP_ITEM;
	
	@Override
	public String toString() {
		
		switch (this) {
			
			case MAP:
				return "Map";
				
			case HEALING_ITEM:
				return "Healing Item";
				
			case POWER_UP_ITEM:
				return "Power Up Item";
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
}
