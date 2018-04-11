package game.minigame;

/**
 * Exactly what is sounds like...
 * @author jesse
 *
 */
public enum PaperScissorsRockMove {

	PAPER,
	SCISSORS,
	ROCK;
	
	public static PaperScissorsRockMove fromString(String str) {
		
		switch (str.trim().toLowerCase()) {
		
		case "rock":
			return PaperScissorsRockMove.ROCK;
			
		case "paper":
			return PaperScissorsRockMove.PAPER;
			
		case "scissors":
			return PaperScissorsRockMove.SCISSORS;
			
		default:
			return null;
		
		}
		
	}
	
	@Override
	public String toString() {
		
		switch (this) {
		
		case PAPER:
			return "paper";
			
		case ROCK:
			return "rock";
			
		case SCISSORS:
			return "scissors";
			
		default:
			return "null";
		
		}
		
	}
	
}
