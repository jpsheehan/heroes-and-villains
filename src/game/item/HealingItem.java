package game.item;

/**
 * The HealingItem class as described in section 3.6 of the specification.
 *
 */
public class HealingItem extends Item {
	
	/**
	 * The amount of health restored by the HealingItem. Health is restored in 25% increments.
	 * For example, a restorationLevel of 2 would restore 50% of the Hero's health.
	 */
	private Integer restorationLevel;
	
	/**
	 * The time (in seconds) for all 25% increments of health to be applied to the Hero.
	 */
	private Integer applicationTime;
	
	/**
	 * Creates a new HealingItem.
	 * @param name The name of the HealingItem.
	 * @param price The price of the HealingItem.
	 * @param restorationLevel The restoration level of the HealingItem. Health is restored in 25% segments, e.g. a restoration level of 2 restores 50% of the Hero's health.
	 * @param applicationTime The time (in seconds) to restore the specified amount of health.
	 */
	public HealingItem(String name, Integer price, Integer restorationLevel, Integer applicationTime) {
		super(name, price);
		
		if (restorationLevel < 1 || restorationLevel > 4) {
			throw new IllegalArgumentException("restorationLevel must be between 1 and 4 (inclusive), but was " + restorationLevel.toString());
		}
		
		this.restorationLevel = restorationLevel;
		
		if (applicationTime < 1) {
			throw new IllegalArgumentException("applicationTime must be a postive integer, but was " + applicationTime.toString());
		}
		
		this.applicationTime = applicationTime;
	}
	
	/**
	 * Returns the restoration level of the HealingItem. Health is restored in 25% increments, so a restoration level of 2 will restore 50% of the Hero's health.
	 */
	public Integer getRestorationLevel() {
		return this.restorationLevel;
	}
	
	/**
	 * Returns the time (in seconds) for the Hero's health to be restored by the amount specified by the restoration level.
	 * @return
	 */
	public Integer getApplicationTime() {
		return this.applicationTime;
	}

}

// types of healing items:
/*
 * two dollar rice - heals for 25% - $2
 * shilling burger - 100% - $20
 * coffee and muffin - %50 - $5 // $8 when not a thursday
 */ // research required