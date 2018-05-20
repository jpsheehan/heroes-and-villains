package game.city;

import java.io.Serializable;

/**
 * The direction an area resides in. The home base always resides in the centre.
 *
 */
public enum Direction implements Serializable {
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
