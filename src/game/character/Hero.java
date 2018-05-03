package game.character;

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
	 * @throws Exception 
	 */
	public Integer getRecoveryRate() {
		
		// TODO: Change these:
		
		switch (this.type) {
		
			case ARTS_STUDENT:
				return 2;
			
			case COMMERCE_STUDENT:
				return 2;
				
			case COMPUTER_SCIENCE_STUDENT:
				return 2;
				
			case ENGINEERING_STUDENT:
				return 2;
				
			case LAW_STUDENT:
				return 2;
				
			case MATHS_STUDENT:
				return 2;
				
			case SCIENCE_STUDENT:
				return 2;
				
			default:
				throw new AssertionError("You shouldn't get this. Is there another HeroType that we don't know about?");
			
		}
		
	}
	
	/**
	 * Returns the current health of the Hero.
	 * @return
	 */
	public Integer getHealth() {
		
		return this.health;
		
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
		
		if (this.health <= 0) {
			
			this.health = 0;
			throw new HeroDeadException(this);
			
		}
		
	}
	
	/**
	 * Gets whether the hero is alive or not.
	 * @return True if the Hero is alive.
	 */
	public boolean isAlive() {
		
		return (this.health > 0);
		
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
}
