package game;

import java.util.Random;

public class PaperScissorsRock extends Minigame<String, String, String> {

	/**
	 * Creates a new PaperScissorsRock game
	 * Paper beats rock, Scissors beat paper, Rock beats Scissors.
	 * @param abilities The abilities to include in the game.
	 */
	public PaperScissorsRock(Ability[] abilities) {
		super(abilities);
		availableChoices[0] = "Paper";
		availableChoices[1] = "Scissors";
		availableChoices[2] = "Rock";
	}

	/**
	 * String array contains game options: Paper, Scissors, Rock
	 */
	// what about a Joker?
	private String[] availableChoices = new String[3];
	
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
	private String villainChoice = availableChoices[rand.nextInt(2)];
	
	/*might be useful to have a list of options
	String toString() {
		return availableChoices;
		
	}*/
	
	@Override
	public void doTurn(String choice) {
		heroChoice = choice;
		if (choice == null) {
			
			throw new IllegalArgumentException("choice should not be null for PaperScissorsRock game.");
		}
		if (state == MinigameState.PLAYING) {
			
			//currently these are hardcoded, would be better to make variables / constants
			// hmmm these are all hardcoded with leading capitals
			if (heroChoice == "Paper") {
				if (villainChoice == "Scissors") {
					state = MinigameState.LOST;
				}
				if (villainChoice == "Rock") {
					state = MinigameState.WON;
				}
			}
			
			if (heroChoice == "Scissors") {
				if (villainChoice == "Rock") {
					state = MinigameState.LOST;
				}
				if (villainChoice == "Paper") {
					state = MinigameState.WON;
				}
			}
			
			if (heroChoice == "Rock") {
				if (villainChoice == "Paper") {
					state = MinigameState.LOST;
				}
				if (villainChoice == "Scissors") {
					state = MinigameState.WON;
				}
			}
			
			if (heroChoice == villainChoice) {
				
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

}
