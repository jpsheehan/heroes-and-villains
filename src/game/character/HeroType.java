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
	
	public static int HERO_TYPE_COUNT = HeroType.values().length;
	
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
				throw new AssertionError();
		
		}
		
	}
	
	/**
	 * Returns the HeroAbility of the HeroType.
	 * @return
	 */
	public HeroAbility getAbility() {
		
		switch (this) {
			
			case ARTS_STUDENT:
				return HeroAbility.THROW_MONEY;
				
			case COMMERCE_STUDENT:
				return HeroAbility.CHEAPER_ITEMS;
				
			case COMPUTER_SCIENCE_STUDENT:
				return HeroAbility.HACK_MAINFRAME;
				
			case ENGINEERING_STUDENT:
				return HeroAbility.DAMAGE_REDUCTION;
				
			case LAW_STUDENT:
				return HeroAbility.PREVENTS_ROBBERY;
				
			case MATHS_STUDENT:
				return HeroAbility.IMPROVED_ODDS;
				
			case SCIENCE_STUDENT:
				return HeroAbility.INCREASED_RECOVERY_RATE;
			
			default:
				throw new AssertionError();
				
		}
		
	}
	
}
