package game.item;

import game.Ability;

public enum ItemAbility implements Ability {
	WIN_ON_DRAW, // for dice game and paper scissors rock
	DAMAGE_PROTECTION, // (single use) stops a hero from taking damage once
	INCREASE_GIFT_CHANCE,
	DECREASE_ROBBERY_CHANCE, 
	FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER; // applies to the next guess the number game
	
	@Override
	public String toString() {
		
		switch (this) {
		
		case DAMAGE_PROTECTION:
			return "damage protection";
			
		case DECREASE_ROBBERY_CHANCE:
			return "decrease robbery chance";
			
		case FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER:
			return "four attempts at guess the number";
			
		case INCREASE_GIFT_CHANCE:
			return "increased gift chance";
			
		case WIN_ON_DRAW:
			return "win on draw";
			
		default:
			return "null";
		
		}
		
	}
}


// win on draw - Grade scaling
// damage protection - Special consideration
// increase gift chance - ??? TODO: think
// decrease robbery chance - bike lock
// four attempts at number game - Resit