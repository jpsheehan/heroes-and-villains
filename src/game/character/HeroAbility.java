package game.character;

import game.Ability;
import game.Describable;
import game.Nameable;

import static game.GeneralHelpers.getString;

/**
 * Represents the different kinds of abilities that Heroes can have.
 * TODO: The exact abilities will need to be discussed.
 *
 */
public enum HeroAbility implements Ability, Nameable, Describable {
	CHEAPER_ITEMS, // for commerce students
	DAMAGE_REDUCTION, // (20%) engineering students (body odour)
	INCREASED_RECOVERY_RATE, // (for team) science students
	THROW_MONEY, // (useless) arts students
	IMPROVED_ODDS, // (for dice game and guess the number) mathematics students
	PREVENTS_ROBBERY, // law students
	HACK_MAINFRAME; // (allows the team to teleport to the next city (one time use)) compsoc. students
	
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
				
			case THROW_MONEY:
				return ("Ability.Hero.ThrowMoney");
				
			default:
				throw new AssertionError();
			
		}
		
	}
	
	@Override
	public String getName() {
		
		return getString(this.getProperName() + ".Name");
		
	}
	
	@Override
	public String getFlavourText() {
		
		return getString(this.getProperName() + ".Flavour");
		
	}
	
}
