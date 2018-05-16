package game.character;

import game.minigame.*;


/**
 * Represents a Villain as described in section 3.3 of the specification.
 */
public class Villain extends Character {

	// 20180416 To Do
	// get favourite games
	// test 
	
	public Villain(String name, String taunt, MinigameType game, int winsToDefeat) {
		
		super(name);
		
		this.winsToDefeat = winsToDefeat;
		this.timesBeaten = 0;
		this.favouriteGame = new MinigameType[] { game };
		this.taunt = taunt;
	}
	
	/**
	 * Creates a new Villain.
	 * @param name The name of the Villain.
	 * @param type The type of Villain.
	 * @param int The number of (minigame) wins required to defeat the Villain 
	 * @param MiniGame one of more minigames the villain plays 
	 */
	public Villain(String name, String taunt, MinigameType[] games, int winsToDefeat) {
		
		super(name);
		
		this.winsToDefeat = winsToDefeat;
		this.timesBeaten = 0;
		this.favouriteGame = games;
		this.taunt = taunt;
		
	}
	
	//return favourite game, or if a list of games randomly choose a game and return it

	/**
 	* The numbers of wins required to defeat the Villain.
 	*/
	private int winsToDefeat;

	/**
 	* The numbers of wins required to beat the Villain.
 	*/
	private int timesBeaten;
	
	private String taunt;
	
	/**
 	* The strength level of the Villain.
 	* Scale of 1-10: 10 doing greatest damage, 1 doing least damage.
 	*/
	private int strength;
	
	/**
	 * Favourite game (if only one entry) or (multiple) games the Villain plays.
	 */
	private MinigameType[] favouriteGame;
	
	/*/**
	 * The Villans's taunt phrase
	 */
	//private String taunt;

	/**
	 * Returns the number of wins required to defeat the Villain.
	 * @return
	 */
	public int getWinsToDefeat() {
		
		return this.winsToDefeat;
		
	}
	
	//Not sure if need this
	/**
	 * Returns the number of times the Villain has been beaten.
	 * @return
	 */
	public int getTimesBeaten() {
		
		return this.timesBeaten;
		
	}
	
	/**
	 * Returns the strength of the Villain.
	 * @return
	 */
	public int getStrength() {
		
		return this.strength;
		
	}
	
	/**
	 * Returns the Villain's taunt phrase (depends on Villain Type).
	 * Currently hardcoded in this class to Villain Type.
	 * @return
	 */
	public String getTaunt() {
		
		return taunt;
		
	}
	
	/**
	 * Returns (single) favourite game or (multiple) games the Villain plays.
	 * @return
	 */
	public MinigameType[] getFavouriteGames() {
		
		return favouriteGame;
		
	}

	/**
	 * Updates the number of times the Villain has been beaten.
	 * @return
	 * @throws VillainDeadException 
	 */
	public void beat() throws VillainDeadException {
		
		this.timesBeaten++;
		
		if (this.timesBeaten >= this.winsToDefeat) {
			
			this.timesBeaten = this.winsToDefeat;
			
		}
	}
	/*
	public static void main(String[] args) {
		
		//create some Minigames, create some Arraylists of minigames
		// add the Minigames into the ArrayLists 
		
	    	//create some Villains
		Villain v1 = new Villain("Front Desk", VillainType.LIBRARIAN, MinigameType.ALL, 1);
		Villain v2 = new Villain("Level 5 ECE Support", VillainType.ADMINISTRATION, MinigameType.ALL, 2);
		Villain v3 = new Villain("I'll Lobb you", VillainType.RICHARD_LOBB, MinigameType.ALL, 6);
		Villain v4 = new Villain("Ker-ching", VillainType.OVERPRICED_TEXTBOOK, MinigameType.DICE_ROLLS, 5);
		Villain v5 = new Villain("Sssss-simon", VillainType.SIMON_BROWN, MinigameType.PAPER_SCISSORS_ROCK, 8);
		Villain v6 = new Villain("Level 5 ECE Support", VillainType.RORY_THE_BUILDER, MinigameType.GUESS_THE_NUMBER, 4);
		
		//print names
		System.out.println(v1.getName());
		System.out.println(v2.getName());
		System.out.println(v3.getName());
		System.out.println(v4.getName());
		System.out.println(v5.getName());
		System.out.println(v6.getName());
	
		//print strength and wins to defeat
		System.out.println(v1.getStrength()+v1.getWinsToDefeat());
		System.out.println(v2.getStrength()+v2.getWinsToDefeat());
		System.out.println(v3.getStrength()+v3.getWinsToDefeat());
		System.out.println(v4.getStrength()+v4.getWinsToDefeat());
		System.out.println(v5.getStrength()+v5.getWinsToDefeat());
		System.out.println(v6.getStrength()+v6.getWinsToDefeat());
		
		//print taunts
		System.out.println(v1.getTaunt());
		System.out.println(v2.getTaunt());
		System.out.println(v3.getTaunt());
		System.out.println(v4.getTaunt());
		System.out.println(v5.getTaunt());
		System.out.println(v6.getTaunt());
	}
	*/
}
