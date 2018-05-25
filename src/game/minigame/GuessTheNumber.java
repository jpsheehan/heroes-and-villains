package game.minigame;

import game.Ability;
import game.GeneralHelpers;
import game.character.HeroAbility;
import game.item.ItemAbility;

/**
 * Implements the Guess The Number between 1 - 10 (mini)game as listed in section 3.4 of the specification 
 * @author jesse
 * @version 1.0
 * 
 * @author jesse
 * @version 2.0
 * Added exception Error if doTurn() is called on a game that has already finished
 * Added isolated tests in main()
 */
public class GuessTheNumber extends Minigame<Integer, Integer, GuessTheNumberAnswer> {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 3480301625315389239L;
	private int guessesRemaining = 2;
	private int maxNumber = 10;
	private int secretNumber;
	
	private int previousGuess;
	private GuessTheNumberAnswer previousAnswer;

	public GuessTheNumber(Ability[] abilities) {
		super(abilities);
		
		// Add more guesses if applicable.
		if (hasAbility(ItemAbility.FOUR_ATTEMPTS_AT_GUESS_THE_NUMBER)) {
			
			guessesRemaining = 4;
			
		}
		
		// Lower the maximum number if applicable.
		if (hasAbility(HeroAbility.IMPROVED_ODDS)) {
			
			maxNumber = 8;
			
		}
		
		// Generate the secret number.
		secretNumber = generateRandomNumber();
	}
	
	/**
	 * Returns a random integer between 1 and 10 (inclusive).
	 * @return
	 */
	private int generateRandomNumber() {
		
		int number = GeneralHelpers.getRandom().nextInt(maxNumber) + 1;
		return number;
		
	}

	/**
	 * 
	 */
	@Override
	public void doTurn(Integer choice) {
		
		if (state == MinigameState.PLAYING) {
			
			guessesRemaining--;
			
			// If the number has been guessed, set the state to reflect that.
			if (choice == secretNumber) {
				
				state = MinigameState.WON;
				previousAnswer = GuessTheNumberAnswer.CORRECT;
				
			} else {
				
				if (choice < secretNumber) {
					
					previousAnswer = GuessTheNumberAnswer.TOO_LOW;
					
				} else {
					
					previousAnswer = GuessTheNumberAnswer.TOO_HIGH;
					
				}
				
			}
			
			// If there are no more moves left and the game has not yet been won, the game has been lost.
			if (guessesRemaining < 1 && state == MinigameState.PLAYING) {
				
				state = MinigameState.LOST;
				
			}
			
		} else {
			
			throw new Error("Cannot call doTurn on a game that has finished.");
			
		}

	}

	/**
	 * Returns the last number the Hero guessed.
	 */
	@Override
	public Integer getHeroLastTurn() {
		return previousGuess;
	}

	/**
	 * Returns the result of the previous guess.
	 */
	@Override
	public GuessTheNumberAnswer getVillainLastTurn() {
		return previousAnswer;
	}

	/**
	 * Returns the number of guesses the Hero has left.
	 */
	@Override
	public int getRemainingTurns() {
		
		if (state == MinigameState.PLAYING) {
			
			return guessesRemaining;
			
		} else {
			
			return 0;
			
		}
	}

	/**
	 * @return MinigameType.GUESS_THE_NUMBER.
	 */
	@Override
	public MinigameType getType() {
		return MinigameType.GUESS_THE_NUMBER;
	}
	
	/**
	 * @return The maximum number that can be guessed.
	 */
	public int getMaxNumber() {
		return maxNumber;
	}
	
	/**
	 * @return The minimum number that can be guessed (always 1).
	 */
	public int getMinNumber() {
		return 1;
	}
	
	/**
	 * @return The secret number if the game is over.
	 */
	public int getSecretNumber() {
		
		if (state != MinigameState.PLAYING) {
			
			return secretNumber;
			
		} else {
			
			throw new Error("You can't see the secret number without finishing the game!");
			
		}
		
	}

}
