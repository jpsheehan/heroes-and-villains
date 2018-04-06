package game;

/**
 * Represents a PowerUpItem as described in section 3.5 of the specification.
 *
 */
public class PowerUpItem extends Item {
	
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
	 * @param price The price of the PowerUpItem.
	 * @param ability The ability the PowerUpItem has.
	 * @param appliesTo The game(s) that the ability applies to.
	 */
	public PowerUpItem(String name, Integer price, ItemAbility ability, MinigameType appliesTo) {
		super(name, price);
		
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
