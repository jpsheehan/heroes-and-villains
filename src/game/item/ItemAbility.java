package game.item;

import game.Ability;
import game.Describable;
import game.Nameable;

import static game.GeneralHelpers.getString;

public enum ItemAbility implements Ability, Nameable, Describable {
	WIN_ON_DRAW, // for dice game and paper scissors rock
	DAMAGE_PROTECTION, // (single use) stops a hero from taking damage once
	INCREASE_GIFT_CHANCE,
	DECREASE_ROBBERY_CHANCE, 
	FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER; // applies to the next guess the number game
	
	@Override
	public String getName() {
		
		return getString(this.getProperName() + ".Name");
		
	}
	
	public String getProperName() {

		switch (this) {
		
			case DAMAGE_PROTECTION:
				return getString("Ability.Item.DamageProtection");
				
			case DECREASE_ROBBERY_CHANCE:
				return ("Ability.Item.DecreaseRobberyChance");
				
			case FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER:
				return ("Ability.Item.FourAttemptsAtGuessTheNumber");
				
			case INCREASE_GIFT_CHANCE:
				return ("Ability.Item.IncreaseGiftChance");
				
			case WIN_ON_DRAW:
				return ("Ability.Item.WinOnDraw");
				
			default:
				throw new AssertionError();
		
		}
		
	}
	
	@Override
	public String getFlavourText() {
		
		return getString(this.getProperName() + ".Flavour");
		
	}
	
	public static ItemAbility fromProperName(String name) {
		
		switch (name) {
		
			case "DamageProtection":
			case "Ability.Item.DamageProtection":
				return DAMAGE_PROTECTION;
				
			case "DecreaseRobberyChance":
			case "Ability.Item.DecreaseRobberyChance":
				return ItemAbility.DECREASE_ROBBERY_CHANCE;
				
			case "FourAttemptsAtGuessTheNumber":
			case "Ability.Item.FourAttemptsAtGuessTheNumber":
				return FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER;
				
			case "IncreaseGiftChance":
			case "Ability.Item.IncreaseGiftChance":
				return INCREASE_GIFT_CHANCE;
				
			case "WinOnDraw":
			case "Ability.Item.WinOnDraw":
				return WIN_ON_DRAW;
			
			default:
				throw new AssertionError();
				
		}
		
	}
}


// win on draw - Grade scaling
// damage protection - Special consideration
// increase gift chance - ??? TODO: think
// decrease robbery chance - bike lock
// four attempts at number game - Resit