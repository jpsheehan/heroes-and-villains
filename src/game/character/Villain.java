package game.character;

/**
 * Represents a Villain as described in section 3.3 of the specification.
 */
public class Villain extends Character {

// games
// list of taunt phrases
// taunt phrase, randomly chosen from the list of taunts
// strength ??

	/**
	 * Creates a new Villain.
	 * @param name The name of the Villain.
	 * @param type The type of Villain.
	 * @param int The number of (minigame) wins required to defeat the Villain  
	 */
public Villain(String name, VillainType type, int winsToBeat) {
		super(name);
		this.type = type;
		this.winsToBeat = winsToBeat;
		this.timesBeaten = 0;
	}
	
	//return taunt
	//return favourite game, or if a list of games randomly choose a game and return it

	/**
	 * The type of Villain this is.
	 */
	private VillainType type;

	/**
 	* The numbers of wins required to beat the Villain.
 	*/
	private Integer winsToBeat;

	/**
 	* The numbers of wins required to beat the Villain.
 	*/
	private Integer timesBeaten;

	/**
	 * The Villans's taunt phrase
	 */
	private String taunt;
	
	/**
	 * Returns the type of Villain.
	 * @return
	 */
	public VillainType getType() {
		return this.type;
	}

	/**
	 * Returns the number of wins required to defeat the Villain.
	 * @return
	 */
	public int getWinsToBeat() {
		return this.winsToBeat;
	}
	
	//Not sure if need this
	/**
	 * Returns the number of wins required to defeat the Villain.
	 * @return
	 */
	public int getTimesBeaten() {
		return this.timesBeaten;
	}
	
	/**
	 * Returns the Villain's taunt phrase.
	 * @return
	 */
	public String getTaunt() {
		return taunt;
	}
	
/**
 * Returns the ability of the Hero (depends on its type).
 */
/*
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
	*/
}
