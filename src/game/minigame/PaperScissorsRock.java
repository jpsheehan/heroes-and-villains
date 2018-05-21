package game.minigame;

/*
 * 2018_04_07
 * 1) using the random number to select the array position didn't seem to be working,
 * so used a method to generate the number, then use number returned 
 * 2) If statements now working by using string equals function
 * 3) Removed hard-coding and used protected variables in the if statements
 */

import game.Ability;
import game.GeneralHelpers;
import game.character.HeroAbility;
import game.item.ItemAbility; 
import game.ui.text.TextUserInterfaceHelpers;
import game.ui.text.UserCancelException;
import game.ui.text.UserContinueException;
import game.ui.text.UserQuitException;

public class PaperScissorsRock extends Minigame<PaperScissorsRockMove, PaperScissorsRockMove, PaperScissorsRockMove> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5401974394486244423L;

	private static PaperScissorsRockMove[] availableMoves = new PaperScissorsRockMove[] {
		PaperScissorsRockMove.ROCK,
		PaperScissorsRockMove.PAPER,
		PaperScissorsRockMove.SCISSORS
	};
	
	/**
	 * Creates a new PaperScissorsRock game
	 * Paper beats rock, Scissors beat paper, Rock beats Scissors.
	 * @param abilities The abilities to include in the game.
	 */
	public PaperScissorsRock(Ability[] abilities) {
		super(abilities);
	}
		
	/**
	 * The game option the hero chooses.
	 */
	private PaperScissorsRockMove heroChoice;
		
	/**
	 * The game option the villain chooses.
	 */
	private PaperScissorsRockMove villainChoice;
	
	private PaperScissorsRockMove getVillainMove() {
		
		return availableMoves[GeneralHelpers.getRandomNextInt(3)];
		
	}
	
	@Override
	public void doTurn(PaperScissorsRockMove choice) {
		
		this.heroChoice = choice;
		
		if (choice == null) {
			
			throw new IllegalArgumentException("choice should not be null for PaperScissorsRock game.");
		}
		
		if (state == MinigameState.DRAWN) {
			
			state = MinigameState.PLAYING;
			
		}
		
		if (this.state == MinigameState.PLAYING) {
			
			this.villainChoice = getVillainMove();
			
			// Decide if the game is won via a draw
			if (this.heroChoice == this.villainChoice) {
				
				if (hasAbility(ItemAbility.WIN_ON_DRAW)) {
					
					this.state = MinigameState.WON;
					
				} else {
					
					this.state = MinigameState.DRAWN;
					
				}
				
			} else {
			
				switch (this.heroChoice) {
				
				case ROCK:
					
					if (this.villainChoice == PaperScissorsRockMove.PAPER) {
						
						this.state = MinigameState.LOST;
						
					} else {
						
						this.state = MinigameState.WON;
						
					}
					
					break;
					
				case PAPER:
					
					if (villainChoice == PaperScissorsRockMove.ROCK) {
						
						this.state = MinigameState.WON;
						
					} else {
						
						this.state = MinigameState.LOST;
						
					}
					
					break;
					
				case SCISSORS:
					
					if (villainChoice == PaperScissorsRockMove.PAPER) {
						
						this.state = MinigameState.WON;
						
					} else {
						
						this.state = MinigameState.LOST;
						
					}
					
					break;
				
				}
			}
		}
	}

	@Override
	public PaperScissorsRockMove getHeroLastTurn() {
		return heroChoice;
	}

	@Override
	public PaperScissorsRockMove getVillainLastTurn() {
		return villainChoice;
	}

	@Override
	public int getRemainingTurns() {
		
		if (this.state == MinigameState.PLAYING || this.state == MinigameState.DRAWN) {
			
			return 1;
			
		} else {
			
			return 0;
			
		}
		
	}

	@Override
	public MinigameType getType() {
		
		return MinigameType.PAPER_SCISSORS_ROCK;
		
	}

	public static void main(String[] args) {
		
		Ability[] abilities = new Ability[] {
			ItemAbility.WIN_ON_DRAW
		};
		
		PaperScissorsRock game = new PaperScissorsRock(abilities);
		
		System.out.println(String.format("Enter paper scissors or rock: "));
		
		
		while (game.getState() == MinigameState.PLAYING) {
			
			String input;
			
			try {
				
				input = TextUserInterfaceHelpers.readLine("> ");
				
			} catch (UserQuitException | UserCancelException e) {
				
				return; // The user wants to quit.
				
			} catch (UserContinueException e) {
				
				System.out.println(String.format("Enter paper scissors or rock: "));
				continue;
				
			}
			
			PaperScissorsRockMove move = PaperScissorsRockMove.fromString(input);
			
			// get input again if necessary
			if (move == null) {
				continue;
			}
			
			game.doTurn(move);
			
			System.out.println(String.format("Your Hero chose: " + game.getHeroLastTurn().toString()));
			System.out.println(String.format("The Villain chose: " + game.getVillainLastTurn().toString()));
			System.out.println(String.format("The game was %s!", game.getState().toString().toLowerCase()));
			
			// Print the abilities
			String[] abilityStrings = new String[abilities.length];
			
			for (int i = 0; i < abilities.length; i++) {
				
				Ability ability = abilities[i];
				
				if (ability.getClass() == HeroAbility.class) {
					
					abilityStrings[i] = ((HeroAbility)ability).toString();
					
				} else {
					
					abilityStrings[i] = ((ItemAbility)ability).toString();
					
				}
				
			}
			
			System.out.println(String.format("Your Hero has: " + String.join(", ", abilityStrings))); //could do with a toString override
			
		}
	}
}


