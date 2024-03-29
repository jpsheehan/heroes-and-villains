package game.character;

import static game.GeneralHelpers.*;

import java.io.Serializable;

import static game.GeneralHelpers.getFloat;

import game.Describable;

import game.Nameable;

/**
 * The types of Heroes that exist in the game.
 */
public enum HeroType implements Nameable, Describable, Serializable {
	ENGINEERING_STUDENT,
	COMPUTER_SCIENCE_STUDENT,
	ARTS_STUDENT,
	MATHS_STUDENT,
	LAW_STUDENT,
	COMMERCE_STUDENT;
	
	/**
	 * @return The name of the HeroType.
	 */
	@Override
	public String getName() {
		
		return getString(this.getProperName() + ".Name");
		
	}
	
	/**
	 * @return The description of the HeroType.
	 */
	@Override
	public String getFlavourText() {
		
		return getString(this.getProperName() + ".Flavour");
		
	}
	
	/**
	 * @return The name of this HeroType as it appears in the strings.json file.
	 */
	public String getProperName() {
		
		switch (this) {
		
			case ARTS_STUDENT:
				return ("HeroType.Arts");
				
			case COMMERCE_STUDENT:
				return ("HeroType.Commerce");
				
			case COMPUTER_SCIENCE_STUDENT:
				return ("HeroType.CompSci");
				
			case ENGINEERING_STUDENT:
				return ("HeroType.Eng");
				
			case LAW_STUDENT:
				return ("HeroType.Law");
				
			case MATHS_STUDENT:
				return ("HeroType.Maths");
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
	/**
	 * @return The HeroAbility of the HeroType.
	 */
	public HeroAbility getAbility() {
		
		switch (this) {
			
			case ARTS_STUDENT:
				return HeroAbility.WITTY_PHRASES;
				
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
			
			default:
				throw new AssertionError();
				
		}
		
	}
	
	/**
	 * @return The recovery rate of the HeroType.
	 */
	public float getRecoveryRate() {
		
		return getFloat(this.getProperName() + ".RecoveryRate");
		
	}
	
	/**
	 * @return The maximum health this type of Hero has.
	 */
	public int getMaxHealth() {
		
		return getInt(this.getProperName() + ".MaxHealth");
		
	}
	
}
