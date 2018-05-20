package game.minigame;

import java.io.Serializable;

/**
 * Denotes the type of minigame a Minigame instance is.
 * @author Jesse
 *
 */
public enum MinigameType implements Serializable {
	GUESS_THE_NUMBER,
	DICE_ROLLS,
	PAPER_SCISSORS_ROCK,
	ALL;
	
	public static MinigameType fromProperName(String name) {
		
		switch (name) {
		
			case "GuessTheNumber":
				return MinigameType.GUESS_THE_NUMBER;
				
			case "DiceRolls":
				return MinigameType.DICE_ROLLS;
				
			case "PaperScissorsRock":
				return PAPER_SCISSORS_ROCK;
				
			case "All":
				return MinigameType.ALL;
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
	@Override
	public String toString() {
		
		switch (this) {
		
			case ALL:
				return "All";
				
			case DICE_ROLLS:
				return "Dice Rolls";
				
			case GUESS_THE_NUMBER:
				return "Guess the Number";
				
			case PAPER_SCISSORS_ROCK:
				return "Paper Scissors Rock";
			
			default:
				throw new AssertionError();
		
		}
		
	}
}
