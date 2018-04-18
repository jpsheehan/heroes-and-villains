package game.character;

import java.util.ArrayList;
import game.minigame.*;

import game.character.VillainType;

/**
	 * Represents a Villain as described in section 3.3 of the specification.
	 */
	public class Villain extends Character {

	// 20180416 To Do
	// get favourite games
	// test 
		
	/**
	 * Creates a new Villain.
	 * @param name The name of the Villain.
	 * @param type The type of Villain.
	 * @param int The number of (minigame) wins required to defeat the Villain 
	 * @param MiniGame one of more minigames the villain plays 
	 */
	public Villain(String name, VillainType type, ArrayList<Minigame> games, int winsToDefeat) {
		super(name);
		this.type = type;
		this.winsToDefeat = winsToDefeat;
		this.timesBeaten = 0;
		this.favouriteGame = games;
	}
	
	//return favourite game, or if a list of games randomly choose a game and return it

	/**
	 * The type of Villain this is.
	 */
	private VillainType type;

	/**
 	* The numbers of wins required to defeat the Villain.
 	*/
	private Integer winsToDefeat;

	/**
 	* The numbers of wins required to beat the Villain.
 	*/
	private Integer timesBeaten;
	
	/**
 	* The strength level of the Villain.
 	* Scale of 1-10: 10 doing greatest damage, 1 doing least damage.
 	*/
	private Integer strength;
	
	/**
	 * Favourite game (if only one entry) or (multiple) games the Villain plays.
	 */
	private ArrayList<Minigame> favouriteGame;
	
	/*/**
	 * The Villans's taunt phrase
	 */
	//private String taunt;
	
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
		switch (this.type) {
		case OVERPRICED_TEXTBOOK:
			return "That'll be $476.97 please. Paying by eftpos? There is %4 surcharge for credit cards";
		case LIBRARIAN:
			return "Don't you know the Dewey Decimal System? What, you've never heard of the Library of Congress either?";
		case ADMINISTRATION:
			return "All courses are full. No you cannot change modules. You'll need to apply in writing within five workign days.";
		case RICHARD_LOBB:
			return "Richard strikes again!";
		case SIMON_BROWN:
			return "Physics is fun!";
		case RORY_THE_BUILDER:
			return "Building schmilding. Bet you don't have tools like mine!";
		default :
			return ("You suck big time!");						//shoudn't get this, but could change to throw an exception
		}
	}
	
	/**
	 * Returns (single) favourite game or (multiple) games the Villain plays.
	 * @return
	 */
	public ArrayList<Minigame> getFavouriteGame() {
		return favouriteGame;
	}

	/**
	 * Updates the number of times the Villain has been beaten.
	 * @return
	 */
	public void decreaseTimesBeaten() {
		this.timesBeaten-=1;
	}
	
public static void main(String[] args) {
	
	//create some Minigames, create some Arraylists of minigames
	// add the Minigames into the ArrayLists 
	
    	//create some Villains
	Villain v1 = new Villain("Front Desk", VillainType.LIBRARIAN, 3, 1);
	Villain v2 = new Villain("Level 5 ECE Support", VillainType.ADMINISTRATION, 3, 2);
	Villain v3 = new Villain("I'll Lobb you", VillainType.RICHARD_LOBB, 3, 6);
	Villain v4 = new Villain("Ker-ching", VillainType.OVERPRICED_TEXTBOOK, 3, 5);
	Villain v5 = new Villain("Sssss-simon", VillainType.SIMON_BROWN, 3, 8);
	Villain v6 = new Villain("Level 5 ECE Support", VillainType.RORY_THE_BUILDER, 3, 4);
	
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
}
