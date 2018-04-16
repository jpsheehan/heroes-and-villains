package game.character;

/**
 * The exception to be thrown when a Hero dies in battle.
 * @author jesse
 *
 */
public class HeroDeadException extends Exception {

	/**
	 * Exceptions need this for some reason.
	 */
	private static final long serialVersionUID = 2470738778819759982L;
	
	/**
	 * The hero that died.
	 */
	private Hero hero;
	
	/**
	 * Creates a new HeroDeadException.
	 * @param hero The hero that died.
	 */
	public HeroDeadException(Hero hero) {
		
		super(String.format("The hero '%s' has died.", hero.getName()));
		this.hero = hero;
		
	}
	
	/**
	 * Gets the hero that died.
	 * @return The hero that died.
	 */
	public Hero getHero() {
		
		return this.hero;
		
	}
}
