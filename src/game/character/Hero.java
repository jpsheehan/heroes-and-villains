package game.character;

import java.io.Serializable;
import java.util.Date;

import game.GeneralHelpers;
import game.item.HealingItem;
import game.item.PowerUpItem;

/**
 * Represents a Hero as described in section 3.2 of the specification.
 */
public class Hero extends Character implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -4935741977219828138L;

	/**
	 * The type of Hero this is.
	 */
	private HeroType type;
	
	/**
	 * The current health of the Hero.
	 */
	private int health;
	
	/**
	 * The power up item the Hero is using/holding.
	 */
	private PowerUpItem item = null;
	
	/**
	 * The time (in milliseconds since the epoch) when the HealingItem is applied.
	 */
	private long healingStartTime;
	
	/**
	 * The HealingItem that is being used.
	 */
	private HealingItem healingItem;
	
	/**
	 * Creates a new Hero.
	 * @param name The name of the Hero.
	 * @param type The type of the Hero.
	 */
	public Hero(String name, HeroType type) {
		
		super(name);
		
		this.type = type;
		this.health = 100;
		
	}
	
	/**
	 * Returns the type of the Hero.
	 * @return
	 */
	public HeroType getType() {
		
		return this.type;
		
	}
	
	/**
	 * Returns the ability of the Hero (depends on its type).
	 */
	public HeroAbility getAbility() {
		
		switch (this.type) {
		
			case ARTS_STUDENT:
				return HeroAbility.WITTY_PHRASES;
				
			case COMMERCE_STUDENT:
				return HeroAbility.CHEAPER_ITEMS;
				
			case COMPUTER_SCIENCE_STUDENT:
				return HeroAbility.HACK_MAINFRAME;
				
			case ENGINEERING_STUDENT:
				return HeroAbility.DAMAGE_REDUCTION;
				
			case LAW_STUDENT:
				return HeroAbility.PREVENTS_ROBBERY;
				
			case MATHS_STUDENT:
				return HeroAbility.IMPROVED_ODDS;
				
			case SCIENCE_STUDENT:
				return HeroAbility.INCREASED_RECOVERY_RATE;
				
			default:
				throw new AssertionError("You shouldn't get this. Is there another HeroType that we don't know about?");
			
		}
		
	}
	
	/**
	 * Returns the recovery rate of the Hero (depends on its type).
	 * The recovery rate is a multiplier used when calculating the amount of time to heal.
	 * A value of < 1 means that the hero will heal slower.
	 * A value of > 1 means that the hero will heal faster.
	 */
	public float getRecoveryRate() {
		
		return this.getType().getRecoveryRate();
		
	}
	
	/**
	 * @return The current health of the Hero.
	 */
	public int getHealth() {
		
		if (this.healingItem == null) {
			
			// If we aren't healing then the health attribute is accurate.
			return this.health;
			
		} else {
			
			// Otherwise, the health must be calculated...
			float percentHealed = (float) GeneralHelpers.min((float)((new Date()).getTime() - healingStartTime) / (1000 * healingItem.getApplicationTime() / this.getRecoveryRate()), 1f);
			float amountToHeal = (float) (healingItem.getRestorationLevel() * 0.25 * getMaxHealth());
			int calculatedHealth = (int) GeneralHelpers.min(this.health + (int)(percentHealed * amountToHeal), getMaxHealth());
			
			if (percentHealed == 1f || calculatedHealth == getMaxHealth()) {

				// if fully healed, set the health attribute accordingly (depending on whether the hero's health has exceed 100% or the time has run out on the healing item)
				if (calculatedHealth > getMaxHealth()) {
					
					this.health = getMaxHealth();
					
				} else {
					
					this.health = calculatedHealth;
					
				}
				
				// reset the healing item stuff
				this.healingItem = null;
				this.healingStartTime = 0;
				
			}
			
			return calculatedHealth;
			
		}
		
	}
	
	/**
	 * @return The remaining time (in seconds) that the healing item will take to be used.
	 */
	public int getRemainingHealingTime() {
		
		if (!isHealing()) {
			
			return 0;
			
		} else {
			
			return (int) ((healingItem.getApplicationTime() / this.getRecoveryRate()) - (int)(((float)((new Date()).getTime() - healingStartTime)) / 1000f));
			
		}
		
	}
	
	/**
	 * Deals some amount of damage to the Hero.
	 * @param damage The amount of damage to deal.
	 * @throws HeroDeadException if the hero is dead.
	 */
	public void takeDamage(int damage) throws HeroDeadException {
		
		if (damage < 0) {
			
			throw new IllegalArgumentException("Damage should not be negative!");
			
		}
		
		this.health -= damage;
		
		if (this.getHealth() <= 0) {
			
			// if we have less than zero health, set it to zero
			this.health = 0;
			throw new HeroDeadException(this);
			
		}
		
	}
	
	/**
	 * Gets whether the hero is alive or not.
	 * @return True if the Hero is alive. False otherwise.
	 */
	public boolean isAlive() {
		
		return (this.getHealth() > 0);
		
	}
	
	/**
	 * To be called when the team is in a power up den. Applies an item to a hero.
	 * @param item The item to apply.
	 */
	public void usePowerUpItem(PowerUpItem item) {
		
		this.item = item;
		
	}
	
	/**
	 * @return true if the hero is holding an item.
	 */
	public boolean hasPowerUpItem() {
		
		return this.item != null;
		
	}
	
	/**
	 * Removes the power up item after it has been "used". Called internally by the BattleScreen after the battle is over.
	 */
	public void destroyPowerUpItem() {
		
		this.item = null;
		
	}
	
	/**
	 * @return The currently held power up item.
	 */
	public PowerUpItem getPowerUpItem() {
		
		return this.item;
		
	}
	
	/**
	 * @return The name and major of the Hero.
	 */
	@Override
	public String toString() {
		
		return String.format("%s (%s Student)", this.getName(), this.getType().getName());
		
	}
	
	/**
	 * Uses a healing item on the hero.
	 * @param item The healing item to use.
	 */
	public void useHealingItem(HealingItem item) {
		
		if (isHealing()) {
			
			throw new IllegalArgumentException("Hero is already healing");
			
		}
		
		this.healingItem = item;
		this.healingStartTime = (new Date()).getTime();
		
	}
	
	/**
	 * @return True if the hero is currently healing.
	 */
	public boolean isHealing() {
		
		return this.healingItem != null;
		
	}
	
	/**
	 * @return The maximum health of the hero.
	 */
	public int getMaxHealth() {
		
		return type.getMaxHealth();
		
	}
	
	/**
	 * @return The currently held healing item (or null).
	 */
	public HealingItem getHealingItem() {
		
		return this.healingItem;
		
	}
}
