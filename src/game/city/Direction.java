package game.city;
/**
 * The direction an area resides in. The home base always resides in the centre.
 *
 */
public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST,
	CENTRE;
	
	public String toString() {
		
		switch (this) {
			
			case CENTRE:
				return "Centre";
				
			case EAST:
				return "East";
				
			case NORTH:
				return "North";
				
			case SOUTH:
				return "South";
				
			case WEST:
				return "West";
			
			default:
				return "null";
		
		}
		
	}
	
}
