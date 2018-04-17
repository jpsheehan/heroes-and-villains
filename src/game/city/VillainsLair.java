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
	 * The index of the current city (used in money/damage calculations)
	 */
	private int cityIndex;
	
	/**
	 * Creates a new Villains Lair.
	 * @param name The name of the Home Base.
	 */
	public VillainsLair(String name, Villain villain, int cityIndex) {
		super(name, AreaType.VILLAINS_LAIR);
		this.villain = villain;
		this.cityIndex = cityIndex;
		battleScreen = new BattleScreen(villain, cityIndex); 
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

	/**
	 * @return the cityIndex
	 */
	public int getCityIndex() {
		return cityIndex;
	}
				
}
