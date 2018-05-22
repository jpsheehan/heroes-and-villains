package game.item;

import game.Ability;
import game.Describable;
import game.Nameable;

import static game.GeneralHelpers.getString;

import java.io.Serializable;

/**
 * An ability that items have.
 * @author jesse
 */
public enum ItemAbility implements Ability, Nameable, Describable, Serializable {
	
	/**
	 * For Dice Game and Paper, Scissors, Rock.
	 */
	WIN_ON_DRAW,
	
	/**
	 * Stops a single hero from taking damage once.
	 * TODO: Implement
	 */
	DAMAGE_PROTECTION,
	
	/**
	 * Increases the amount of money the hero gets if this hero defeats the villain.
	 * TODO: Implement
	 */
	INCREASE_GIFT_CHANCE,
	
	/**
	 * TODO: Change this!!!
	 */
	DECREASE_ROBBERY_CHANCE,
	
	/**
	 * Applies to the Guess the Number game.
	 */
	FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER;
	
	/**
	 * @return The human-readable name of the Item ability.
	 */
	@Override
	public String getName() {
		
		return getString(this.getProperName() + ".Name");
		
	}
	
	/**
	 * @return The name of the item ability as it appears in the strings.json file.
	 */
	public String getProperName() {

		switch (this) {
		
			case DAMAGE_PROTECTION:
				return "Ability.Item.DamageProtection";
				
			case DECREASE_ROBBERY_CHANCE:
				return "Ability.Item.DecreaseRobberyChance";
				
			case FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER:
				return "Ability.Item.FourAttemptsAtGuessTheNumber";
				
			case INCREASE_GIFT_CHANCE:
				return "Ability.Item.IncreaseGiftChance";
				
			case WIN_ON_DRAW:
				return "Ability.Item.WinOnDraw";
				
			default:
				throw new AssertionError();
		
		}
		
	}
	
	/**
	 * @return The description of the ability.
	 */
	@Override
	public String getFlavourText() {
		
		return getString(this.getProperName() + ".Flavour");
		
	}
	
	/**
	 * Converts the string into an ItemAbility.
	 * @param name The name to convert.
	 * @return A valid ItemAbility.
	 */
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
