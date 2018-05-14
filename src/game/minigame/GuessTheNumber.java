package game.minigame;

import game.Ability;
import game.GeneralHelpers;
import game.character.HeroAbility;
import game.item.ItemAbility;
import game.ui.text.TextUserInterfaceHelpers;
import game.ui.text.UserCancelException;
import game.ui.text.UserContinueException;
import game.ui.text.UserQuitException;

public class GuessTheNumber extends Minigame<Integer, Integer, GuessTheNumberAnswer> {
	
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
			
			maxNumber = 9;
			
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
	 * Returns MinigameType.GUESS_THE_NUMBER.
	 */
	@Override
	public MinigameType getType() {
		return MinigameType.GUESS_THE_NUMBER;
	}
	
	/**
	 * Returns the maximum number that can be guessed.
	 * @return
	 */
	public int getMaxNumber() {
		return maxNumber;
	}
	
	/**
	 * Returns the minimum number that can be guessed (always 1).
	 * @return
	 */
	public int getMinNumber() {
		return 1;
	}
	
	/**
	 * Returns the secret number if the game is over.
	 * @return
	 */
	public int getSecretNumber() {
		
		if (state != MinigameState.PLAYING) {
			
			return secretNumber;
			
		} else {
			
			throw new Error("You can't see the secret number without finishing the game!");
			
		}
		
	}
	
	/**
	 * Runs a test of the GuessTheNumber game.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Ability[] abilities = new Ability[] {
				
		};
		
		GuessTheNumber game = new GuessTheNumber(abilities);
		
		System.out.println(String.format("Guess a number between %d and %d:", game.getMinNumber(), game.getMaxNumber()));
		
		while (game.getState() == MinigameState.PLAYING) {
			
			int guess;
			
			try {
				
				guess = TextUserInterfaceHelpers.getNumberWithBounds(game.getMinNumber(), game.getMaxNumber());
				
			} catch (UserCancelException | UserQuitException e) {
				
				return; // the user wants to quit
				
			} catch (UserContinueException e) {
				
				System.out.println(String.format("Guess a number between %d and %d:", game.getMinNumber(), game.getMaxNumber()));
				continue;
				
			}
			
			game.doTurn(guess);
			
			if (game.getVillainLastTurn() == GuessTheNumberAnswer.TOO_HIGH) {
				
				System.out.println("Your answer was too high!");
				
			} else {
				
				if (game.getVillainLastTurn() == GuessTheNumberAnswer.TOO_LOW) {
					
					System.out.println("Your answer was too low!");
					
				} else {
					
					System.out.println("Your answer was right!");
					
				}
				
			}
			
		}
		
		if (game.getState() == MinigameState.WON) {
			
			System.out.println("You won the game!");
			
		} else {
			
			System.out.println(String.format("You lost the game! The number was %d.", game.getSecretNumber()));
			
		}
		
		
		
	}

}
