package game.item;

import java.io.Serializable;

/**
 * The HealingItem class as described in section 3.6 of the specification.
 *
 */
public class HealingItem extends Item implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -1452187370893053993L;

	/**
	 * The amount of health restored by the HealingItem. Health is restored in 25% increments.
	 * For example, a restorationLevel of 2 would restore 50% of the Hero's health.
	 */
	private int restorationLevel;
	
	/**
	 * The time (in seconds) for all 25% increments of health to be applied to the Hero.
	 */
	private int applicationTime;
	
	/**
	 * Creates a new HealingItem.
	 * @param name The name of the HealingItem.
	 * @param flavourText The information (description) about the HealingItem
	 * @param price The price of the HealingItem.
	 * @param restorationLevel The restoration level of the HealingItem. Health is restored in 25% segments, e.g. a restoration level of 2 restores 50% of the Hero's health.
	 * @param applicationTime The time (in seconds) to restore the specified amount of health.
	 */
	public HealingItem(String name, String flavourText, int price, int restorationLevel, int applicationTime) {
		super(name, flavourText, price, ItemType.HEALING_ITEM);
		
		if (restorationLevel < 1 || restorationLevel > 4) {
			throw new IllegalArgumentException(
					String.format("restorationLevel must be between 1 and 4 (inclusive), but was %d", restorationLevel));
		}
		
		this.restorationLevel = restorationLevel;
		
		if (applicationTime < 1) {
			throw new IllegalArgumentException(
					String.format("applicationTime must be a postive integer, but was %d", applicationTime));
		}
		
		this.applicationTime = applicationTime;
	}
	
	/**
	 * @return The restoration level of the HealingItem. Health is restored in 25% increments, so a restoration level of 2 will restore 50% of the Hero's health.
	 */
	public int getRestorationLevel() {
		return this.restorationLevel;
	}
	
	/**
	 * @return The time (in seconds) for the Hero's health to be restored by the amount specified by the restoration level.
	 */
	public int getApplicationTime() {
		return this.applicationTime;
	}

}

