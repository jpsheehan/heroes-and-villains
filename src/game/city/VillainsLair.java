package game.city;

import game.character.Villain;
import game.BattleScreen;

/**
 * Represents a Villain's Lair.
 * @author jesse
 */
public class VillainsLair extends Area {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -2893708442508659573L;

	/**
	 * The villain
	 */
	private Villain villain;
	
	/**
	 * The battlescreen
	 */
	private BattleScreen battleScreen;
	
	/**
	 * Creates a new Villains Lair.
	 * @param name The name of the Villains Lair.
	 * @param description The information that relates to the Villains Lair
	 * @param villain The Villain who resides in this Villains Lair
	 */
	public VillainsLair(String name, String description, Villain villain) {
		
		super(name, description, AreaType.VILLAINS_LAIR);
		
		this.villain = villain;
		this.battleScreen = new BattleScreen(villain);
		
	}

	/**
	 * @return The villain in this lair
	 */
	public Villain getVillain() {
		
		return villain;
		
	}

	/**
	 * @return The battleScreen
	 */
	public BattleScreen getBattleScreen() {
		
		return battleScreen;
		
	}
				
}
