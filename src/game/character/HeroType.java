package game.character;

import static game.GeneralHelpers.getString;

/**
 * The types of Heroes that exist in the game.
 *
 */
public enum HeroType {
	ENGINEERING_STUDENT,
	COMPUTER_SCIENCE_STUDENT,
	ARTS_STUDENT,
	MATHS_STUDENT,
	LAW_STUDENT,
	COMMERCE_STUDENT,
	SCIENCE_STUDENT;
	
	@Override
	public String toString() {
		
		switch (this) {
		
			case ARTS_STUDENT:
				return getString("HeroType.Arts");
				
			case COMMERCE_STUDENT:
				return getString("HeroType.Commerce");
				
			case COMPUTER_SCIENCE_STUDENT:
				return getString("HeroType.CompSci");
				
			case ENGINEERING_STUDENT:
				return getString("HeroType.Eng");
				
			case LAW_STUDENT:
				return getString("HeroType.Law");
				
			case MATHS_STUDENT:
				return getString("HeroType.Maths");
				
			case SCIENCE_STUDENT:
				return getString("HeroType.Science");
			
			default:
				return "null";
		
		}
		
	}
}
