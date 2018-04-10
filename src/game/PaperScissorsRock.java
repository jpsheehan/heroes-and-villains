package game;

/*
 * 2018_04_07
 * 1) using the random number to select the array position didn't seem to be working,
 * so used a method to generate the number, then use number returned 
 * 2) If statements now working by using string equals function
 * 3) Removed hard-coding and used protected variables in the if statements
 */

import java.util.Random;
import game.ui.text.TextUserInterfaceHelpers;
import java.util.Scanner;

public class PaperScissorsRock extends Minigame<String, String, String> {

	/**
	 * Creates a new PaperScissorsRock game
	 * Paper beats rock, Scissors beat paper, Rock beats Scissors.
	 * @param abilities The abilities to include in the game.
	 */
	public PaperScissorsRock(Ability[] abilities) {
		super(abilities);
		availableChoices[0] = "paper";
		availableChoices[1] = "scissors";
		availableChoices[2] = "rock";
	}

	/**
	 * String array contains game options: Paper, Scissors, Rock
	 */
	// what about a Joker?
	private String[] availableChoices = new String[3];
	
	/**
	 * constants for user selectable options:
	 * userOption1 = paper, userOption2 = scissors, userOption3 = rock.  
	 */
	protected String userOption1 = "paper";
	protected String userOption2 = "scissors";
	protected String userOption3 = "rock";
	
	/**
	 * Random number generator for villains choice
	 */
	private Random rand = new Random();
		
	/**
	 * The game option the hero chooses.
	 */
	private String heroChoice;
		
	/**
	 * The game option the villain chooses.
	 */
	private String villainChoice; // = availableChoices[rand.nextInt(3)];

	/*might be useful to have a list of options
	String toString() {
		return availableChoices;
	}*/
	
	private int getOptionNumber() {
		Random rand = new Random();
		return rand.nextInt(2) + 1;
	}
	
	@Override
	public void doTurn(String choice) {
		heroChoice = choice;
		if (choice == null) {
			
			throw new IllegalArgumentException("choice should not be null for PaperScissorsRock game.");
		}
		if (state == MinigameState.PLAYING) {
			
			villainChoice = availableChoices[getOptionNumber()];
			if (heroChoice.equals(userOption1)) {					//implemented the equals statement, now working
				/*These lines for testing only
				 * System.out.println("If statement 1, Hero chose: "+ userOption1);
				System.out.println("If statement 1, Villain chose"+ villainChoice);*/

				if (villainChoice.equals(userOption2)) {
					state = MinigameState.LOST;
				}
				if (villainChoice.equals(userOption3)) {
					state = MinigameState.WON;
				}
			}

			if (heroChoice.equals(userOption2)) {
				if (villainChoice.equals(userOption3)) {
					state = MinigameState.LOST;
				}
				if (villainChoice.equals(userOption1)) {
					state = MinigameState.WON;
				}
			}
			
			if (heroChoice.equals(userOption3)) {
				if (villainChoice.equals(userOption1)) {
					state = MinigameState.LOST;
				}
				if (villainChoice.equals(userOption2)) {
					state = MinigameState.WON;
				}
			}
			
			if (heroChoice.equals(villainChoice)) {
				
				// If the hero choice is the same as the villain and they have the WIN_ON_DRAW ability, the hero wins.
				if (hasAbility(ItemAbility.WIN_ON_DRAW)) {
					
					state = MinigameState.WON;	
				} 
				else {		
					state = MinigameState.DRAWN;
				}
			}
		}
	}

	@Override
	public String getHeroLastTurn() {
		// TODO Auto-generated method stub
		return heroChoice;
	}

	@Override
	public String getVillainLastTurn() {
		// TODO Auto-generated method stub
		return villainChoice;
	}

	@Override
	public int getRemainingTurns() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MinigameType getType() {
		// TODO Auto-generated method stub
		return null;
	}

public static void main(String[] args) {
	
	Ability[] abilities = new Ability[] {
			ItemAbility.WIN_ON_DRAW
	};
	
	PaperScissorsRock game = new PaperScissorsRock(abilities);
	
	System.out.println(String.format("Enter paper scissors or rock: "));
	
	
	while (game.getState() == MinigameState.PLAYING) {
		
		Scanner user_input = new Scanner( System.in );
		
		String userSelection;
		userSelection = user_input.next( );
				
		game.doTurn(userSelection);
		
		System.out.println(String.format("Your Hero chose: "+ (String)game.getHeroLastTurn()));
		System.out.println(String.format("The Villain chose: "+ (String)game.getVillainLastTurn()));
		System.out.println(String.format("The game was %s!", game.getState().toString().toLowerCase()));
		System.out.println(String.format("Your Hero has: "+ abilities)); //could do with a toString override
		}
	}
}


