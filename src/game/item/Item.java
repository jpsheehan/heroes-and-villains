package game.item;

import game.Describable;
import game.Nameable;
import game.minigame.MinigameType;

import static game.GeneralHelpers.getString;

import java.io.Serializable;

import static game.GeneralHelpers.getInt;

/**
 * An item such as a map, power up or healing item.
 * @author jesse
 *
 */
public abstract class Item implements Nameable, Describable, Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -760574577254027710L;

	/**
	 * The name of the Item.
	 */
	private String name;
	
	/**
	 * The price of the Item.
	 */
	private int price;
	
	/**
	 * The description of the item.
	 */
	private String flavourText;
	
	/**
	 * The type of item.
	 */
	private ItemType type;
	
	/**
	 * Creates a new Item.
	 * @param name The name of the Item.
	 * @param price The price of the Item.
	 */
	public Item(String name, String flavourText, int price, ItemType type) {
		this.name = name;
		this.flavourText = flavourText;
		this.price = price;
		this.type = type;
	}

	/**
	 * @return The name of the Item.
	 */
	@Override
	public final String getName() {
		return this.name;
	}

	/**
	 * @return The price of the Item.
	 */
	public final int getPrice() {
		return this.price;
	}
	
	/**
	 * @return The flavour text describing this item.
	 */
	@Override
	public final String getFlavourText() {
		return this.flavourText;
	}
	
	/**
	 * @return The type of the item.
	 */
	public final ItemType getType() {
		return this.type;
	}
	
	/**
	 * All items must have Name, Flavour, Price and Type keys.
	 * Valid type values are "Healing", "Map" and "PowerUp".
	 * Healing items require ApplicationTime, RestorationLevel
	 * PowerUp items require Ability, AppliesTo
	 * Valid MinigameTypes for AppliesTo are "PaperScissorsRock", "DiceRolls", "GuessTheNumber" and "All".
	 * @param specifier The item to return.
	 * @param scalePrice Scaling factor to apply to prices
	 * @return An item from the Strings file.
	 */
	public static Item fromStrings(String specifier, float scalePrice) {
		
		specifier = String.format("Item.%s.", specifier);
		
		try {
			
			String name = getString(specifier + "Name");
			String flavour = getString(specifier + "Flavour");
			String type = getString(specifier + "Type");
			int price = (int)(getInt(specifier + "Price") * scalePrice);
			
			switch (type) {
			
			case "Healing":
				
				int applicationTime = getInt(specifier + "ApplicationTime");
				int restorationLevel = getInt(specifier + "RestorationLevel");
				
				return new HealingItem(name, flavour, price, restorationLevel, applicationTime);
				
			case "Map":
				
				return new Map(name, flavour, price);
				
			case "PowerUp":
				
				String abilityString = getString(specifier + "Ability");
				ItemAbility ability = ItemAbility.fromProperName(abilityString);
				
				MinigameType appliesTo = MinigameType.fromProperName(getString(specifier + "AppliesTo"));
				
				return new PowerUpItem(name, flavour, price, ability, appliesTo);
				
				default:
					throw new AssertionError(String.format("Invalid item type \"%s\"", type));
			
			}
			
		} catch (Exception e) {
			
			throw new IllegalArgumentException(String.format("Invalid specifier for item \"%s\"", specifier), e);
			
		}
		
	}
	
	/**
	 * @return The effect that this item has on gameplay.
	 */
	public String getEffect() {
		
		switch (type) {
		
			case MAP:
				return "Reveals all the areas in a building";
				
			case HEALING_ITEM:
				return String.format("Heals %d%% health over %d seconds", ((HealingItem)this).getRestorationLevel() * 25, ((HealingItem)this).getApplicationTime());
				
			case POWER_UP_ITEM:
				return String.format("Gives a hero the %s ability during %s minigames", ((PowerUpItem)this).getAbility().getName(), ((PowerUpItem)this).getAppliesTo().toString());
		
		}
		
		return null;
		
	}

}
