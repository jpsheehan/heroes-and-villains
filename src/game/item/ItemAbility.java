package game.item;

import game.Ability;
import static game.GeneralHelpers.getString;

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
				return getString("Ability.Item.DamageProtection.Name");
				
			case DECREASE_ROBBERY_CHANCE:
				return getString("Ability.Item.DecreaseRobberyChance.Name");
				
			case FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER:
				return getString("Ability.Item.FourAttemptsAtGuessTheNumber.Name");
				
			case INCREASE_GIFT_CHANCE:
				return getString("Ability.Item.IncreaseGiftChance.Name");
				
			case WIN_ON_DRAW:
				return getString("Ability.Item.WinOnDraw.Name");
				
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