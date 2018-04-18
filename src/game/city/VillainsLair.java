package game.city;

import game.character.Villain;
import game.BattleScreen;

public class VillainsLair extends Area {

	//20180416 to do
	//Needs a Villain and a battlescreen in it
	// getters and setters
	
	//20180417
	// added Villain, battlescreen. getters
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
	 * @param name The name of the Home Base.
	 */
	public VillainsLair(String name, Villain villain, int cityIndex) {
		
		super(name, AreaType.VILLAINS_LAIR);
		
		this.villain = villain;
		this.battleScreen = new BattleScreen(villain, cityIndex);
		
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
