package game.character;

import game.Ability;
import game.Describable;
import game.Nameable;

import static game.GeneralHelpers.getString;

import java.io.Serializable;

/**
 * Represents the different kinds of abilities that Heroes can have.
 */
public enum HeroAbility implements Ability, Nameable, Describable, Serializable {
	
	/**
	 * For commerce students.
	 */
	CHEAPER_ITEMS,
	
	/**
	 * For engineering students.
	 */
	DAMAGE_REDUCTION,
	
	/**
	 * For science students.
	 */
	INCREASED_RECOVERY_RATE,
	
	/**
	 * For arts students.
	 */
	WITTY_PHRASES,
	
	/**
	 * For maths students.
	 */
	IMPROVED_ODDS,
	
	/**
	 * For law students.
	 */
	PREVENTS_ROBBERY,
	
	/**
	 * For compsci students.
	 */
	HACK_MAINFRAME;
	
	/**
	 * @return The name of this HeroAbility as it appears in the strings.json file.
	 */
	public String getProperName() {
		
		switch (this) {
		
			case CHEAPER_ITEMS:
				return ("Ability.Hero.CheaperItems");
				
			case IMPROVED_ODDS:
				return ("Ability.Hero.ImprovedOdds");
				
			case INCREASED_RECOVERY_RATE:
				return ("Ability.Hero.IncreasedRecoveryRate");
				
			case PREVENTS_ROBBERY:
				return ("Ability.Hero.PreventsRobbery");
				
			case HACK_MAINFRAME:
				return ("Ability.Hero.HackMainframe");
				
			case DAMAGE_REDUCTION:
				return ("Ability.Hero.DamageReduction");
				
			case WITTY_PHRASES:
				return ("Ability.Hero.WittyPhrases");
				
			default:
				throw new AssertionError();
			
		}
		
	}
	
	/**
	 * @return The name of the ability.
	 */
	@Override
	public String getName() {
		
		return getString(this.getProperName() + ".Name");
		
	}
	
	/**
	 * @return The description of the ability.
	 */
	@Override
	public String getFlavourText() {
		
		return getString(this.getProperName() + ".Flavour");
		
	}
	
}
