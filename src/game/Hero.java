package game;

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
		return null; // TODO: stub
	}
	
	/**
	 * Returns the recovery rate of the Hero (depends on its type).
	 */
	public Integer getRecoveryRate() {
		return null; // TODO: stub
	}
	
	/**
	 * Returns the current health of the Hero.
	 * @return
	 */
	public Integer getHealth() {
		return this.health;
	}
	
}
