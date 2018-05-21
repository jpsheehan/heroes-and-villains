package game.item;

import java.io.Serializable;

import game.minigame.MinigameType;

/**
 * Represents a PowerUpItem as described in section 3.5 of the specification.
 *
 */
public class PowerUpItem extends Item implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -2511622060584756841L;

	/**
	 * The kind of ability this PowerUpItem has.
	 */
	private ItemAbility ability;
	
	/**
	 * The type of game this ability affects.
	 */
	private MinigameType appliesTo;

	/**
	 * Creates a new PowerUpItem.
	 * @param name The name of the PowerUpItem.
	 * @param flavourText The description of the item.
	 * @param price The price of the PowerUpItem.
	 * @param ability The ability the PowerUpItem has.
	 * @param appliesTo The game(s) that the ability applies to.
	 */
	public PowerUpItem(String name, String flavourText, int price, ItemAbility ability, MinigameType appliesTo) {
		super(name, flavourText, price, ItemType.POWER_UP_ITEM);
		
		if (ability == null) {
			
			throw new NullPointerException("ability cannot be null");
			
		}
		
		if (appliesTo == null) {
			
			throw new NullPointerException("appliesTo cannot be null");
			
		}
		
		this.ability = ability;
		this.appliesTo = appliesTo;
	}
	
	/**
	 * Returns the ability that this PowerUpItem has.
	 */
	public ItemAbility getAbility() {
		return this.ability;
	}
	
	/**
	 * Returns the type of minigame that the ability applies to.
	 */
	public MinigameType getAppliesTo() {
		return this.appliesTo;
	}
}
