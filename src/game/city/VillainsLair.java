package game.city;

import game.character.Villain;
import game.BattleScreen;

public class VillainsLair extends Area {

	//20180416 to do
	//Needs a Villain and a battlescreen in it
	// getters and setters
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2893708442508659573L;

	//20180417
	// added Villain, battlescreen, getters
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
	 */
	public VillainsLair(String name, String description, Villain villain) {
		
		super(name, description, AreaType.VILLAINS_LAIR);
		
		this.villain = villain;
		this.battleScreen = new BattleScreen(villain);
		
	}

	/**
	 * @return the villain in this lair
	 */
	public Villain getVillain() {
		
		return villain;
		
	}

	/**
	 * @return the battleScreen
	 */
	public BattleScreen getBattleScreen() {
		
		return battleScreen;
		
	}
				
}
