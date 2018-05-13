package game.character;

import java.util.Date;

import game.GeneralHelpers;
import game.item.HealingItem;
import game.item.PowerUpItem;

/**
 * Represents a Hero as described in section 3.2 of the specification.
 *
 */
public class Hero extends Character {
	
	/**
	 * The type of Hero this is.
	 */
	private HeroType type;
	
	/**
	 * The current health of the Hero.
	 */
	private Integer health;
	
	/**
	 * The power up item the Hero is using/holding.
	 */
	private PowerUpItem item = null;
	
	private long healingStartTime;
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
				return HeroAbility.THROW_MONEY;
				
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
	 * Returns the recovery rate of the Hero (depends on its type). The recovery rate is the amount of health regained every 10 seconds.
	 * TODO: Update this doc
	 * @throws Exception 
	 */
	public float getRecoveryRate() {
		
		return this.getType().getRecoveryRate();
		
	}
	
	/**
	 * Returns the current health of the Hero.
	 * @return
	 */
	public Integer getHealth() {
		
		if (this.healingItem == null) {
			
			return this.health;
			
		} else {
			
			float percentHealed = (float) GeneralHelpers.min((float)((new Date()).getTime() - healingStartTime) / (1000 * healingItem.getApplicationTime()), 1f);
			float amountToHeal = (float) (healingItem.getRestorationLevel() * 0.25 * getMaxHealth());
			int calculatedHealth = (int) GeneralHelpers.min(this.health + (int)(percentHealed * amountToHeal), getMaxHealth());
//			TODO: REMOVE THIS. TESTING
//			if (this.getName().equals("Steve")) {
//				System.out.println(String.format("PercentHealed: %f%%, AmountToHeal: %f%%, CalculatedHealth: %d", percentHealed * 100, amountToHeal, calculatedHealth));
//			}
			
			return calculatedHealth;
			
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
			
			this.health = 0;
			throw new HeroDeadException(this);
			
		}
		
	}
	
	/**
	 * Gets whether the hero is alive or not.
	 * @return True if the Hero is alive.
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
	 * Returns true if the hero is holding an item.
	 * @return
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
	 * Returns the power up item.
	 * @return
	 */
	public PowerUpItem getPowerUpItem() {
		
		return this.item;
		
	}
	
	@Override
	public String toString() {
		
		return String.format("%s (%s Student)", this.getName(), this.getType().getName());
		
	}
	
	/**
	 * Uses a healing item on the hero.
	 * @param item
	 */
	public void useHealingItem(HealingItem item) {
		
		if (isHealing()) {
			
			throw new IllegalArgumentException("Hero is already healing");
			
		}
		
		this.healingItem = item;
		this.healingStartTime = (new Date()).getTime();
		
	}
	
	/**
	 * Returns true if the hero is currently healing.
	 * @return
	 */
	public boolean isHealing() {
		
		if (this.healingItem != null) {
			
			if (
					this.healingStartTime + this.healingItem.getApplicationTime() > (new Date()).getTime() ||
					this.getHealth() > getMaxHealth()
				) {
				
				if (getHealth() > getMaxHealth()) {
					
					this.health = getMaxHealth();
					
				} else {
					
					this.health += (int)(this.getMaxHealth() * (this.healingItem.getRestorationLevel() * 0.25));
					
				}
				
				this.healingItem = null;
				this.healingStartTime = 0;
				return false;
				
			} else {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public int getMaxHealth() {
		
		return 100;
		
	}
	
	public HealingItem getHealingItem() {
		
		return this.healingItem;
		
	}
}
