package game.character;

import game.Ability;

/**
 * Represents the different kinds of abilities that Heroes can have.
 * TODO: The exact abilities will need to be discussed.
 *
 */
public enum HeroAbility implements Ability {
	CHEAPER_ITEMS, // for commerce students
	VILLAINS_LESS_20_HEALTH, // (20%) engineering students (body odour)
	INCREASED_RECOVERY_RATE, // (for team) science students
	WITTY_PHRASES, // (useless) arts students
	IMPROVED_ODDS, // (for dice game and guess the number) maths students
	PREVENTS_ROBBERY, // law students
	TELEPORT; // (allows the team to teleport to the next city (single use)) compsoc students
	
	@Override
	public String toString() {
		
		switch (this) {
		
		case CHEAPER_ITEMS:
			return "cheaper items";
			
		case IMPROVED_ODDS:
			return "improved odds";
			
		case INCREASED_RECOVERY_RATE:
			return "increased recovery rate";
			
		case PREVENTS_ROBBERY:
			return "prevents robbery";
			
		case TELEPORT:
			return "teleport";
			
		case VILLAINS_LESS_20_HEALTH:
			return "villains less 20% health";
			
		case WITTY_PHRASES:
			return "witty phrases";
			
		default:
			return "null";
			
		}
		
	}
}
