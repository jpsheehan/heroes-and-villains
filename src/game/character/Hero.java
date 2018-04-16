package game.character;

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
				return HeroAbility.TELEPORT;
				
			case ENGINEERING_STUDENT:
				return HeroAbility.VILLAINS_LESS_20_HEALTH;
				
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
	public Integer getRecoveryRate() throws Exception {
		
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
					throw new Exception("Invalid HeroType to get the recovery rate of.");
			
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
	
}
