package game.item;

import game.Describable;
import game.Nameable;
import game.minigame.MinigameType;

import static game.GeneralHelpers.getString;

import java.io.Serializable;

import static game.GeneralHelpers.getInt;

public abstract class Item implements Buyable, Nameable, Describable, Serializable {
	
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
	 * Returns the name of the Item.
	 */
	@Override
	public final String getName() {
		return this.name;
	}

	/**
	 * Returns the price of the Item.
	 */
	@Override
	public final int getPrice() {
		return this.price;
	}
	
	/**
	 * Returns the flavour text describing this item.
	 */
	@Override
	public final String getFlavourText() {
		return this.flavourText;
	}
	
	/**
	 * Gets the type of the item.
	 * @return
	 */
	public final ItemType getType() {
		return this.type;
	}
	
	/**
	 * Returns an item from the strings file.
	 * All items must have Name, Flavour, Price and Type keys.
	 * Valid type values are "Healing", "Map" and "PowerUp".
	 * Healing items require ApplicationTime, RestorationLevel
	 * PowerUp items require Ability, AppliesTo
	 * Valid MinigameTypes for AppliesTo are "PaperScissorsRock", "DiceRolls", "GuessTheNumber" and "All".
	 * @param specifier The item to return.
	 * @return
	 */
	public static Item fromStrings(String specifier) {
		
		specifier = String.format("Item.%s.", specifier);
		
		try {
			
			String name = getString(specifier + "Name");
			String flavour = getString(specifier + "Flavour");
			String type = getString(specifier + "Type");
			int price = getInt(specifier + "Price");
			
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

}
