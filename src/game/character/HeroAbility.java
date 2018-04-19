package game.character;

import game.Ability;
import static game.GeneralHelpers.getString;

/**
 * Represents the different kinds of abilities that Heroes can have.
 * TODO: The exact abilities will need to be discussed.
 *
 */
public enum HeroAbility implements Ability {
	CHEAPER_ITEMS, // for commerce students
	DAMAGE_REDUCTION, // (20%) engineering students (body odour)
	INCREASED_RECOVERY_RATE, // (for team) science students
	THROW_MONEY, // (useless) arts students
	IMPROVED_ODDS, // (for dice game and guess the number) mathematics students
	PREVENTS_ROBBERY, // law students
	HACK_MAINFRAME; // (allows the team to teleport to the next city (one time use)) compsoc. students
	
	@Override
	public String toString() {
		
		switch (this) {
		
			case CHEAPER_ITEMS:
				return getString("Ability.Hero.CheaperItems.Name");
				
			case IMPROVED_ODDS:
				return getString("Ability.Hero.ImprovedOdds.Name");
				
			case INCREASED_RECOVERY_RATE:
				return getString("Ability.Hero.IncreasedRecoveryRate.Name");
				
			case PREVENTS_ROBBERY:
				return getString("Ability.Hero.PreventsRobbery.Name");
				
			case HACK_MAINFRAME:
				return getString("Ability.Hero.HackMainframe.Name");
				
			case DAMAGE_REDUCTION:
				return getString("Ability.Hero.DamageReduction.Name");
				
			case THROW_MONEY:
				return getString("Ability.Hero.ThrowMoney.Name");
				
			default:
				return "null";
			
		}	
	}
}
