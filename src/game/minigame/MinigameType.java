package game.minigame;

/**
 * Denotes the type of minigame a Minigame instance is.
 * @author Jesse
 *
 */
public enum MinigameType {
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
}
