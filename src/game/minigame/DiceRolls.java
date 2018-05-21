package game.minigame;

import game.Ability;
import game.GeneralHelpers;
import game.character.HeroAbility;
import game.item.ItemAbility;

public class DiceRolls extends Minigame<Object, Integer, Integer> {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 5190016761538901187L;

	/**
	 * The number the hero rolls.
	 */
	private int heroRoll;
	
	/**
	 * The number the villain rolls.
	 */
	private int villainRoll;
	
	/**
	 * Creates a new DiceRolls game
	 * @param abilities The abilities to include in the game.
	 */
	public DiceRolls(Ability[] abilities) {
		super(abilities);
	}

	/**
	 * Returns the result of a fair dice roll.
	 * @return
	 */
	private int rollDice() {

		return GeneralHelpers.getRandom().nextInt(6) + 1;
		
	}
	
	/**
	 * Performs the turn for the hero.
	 * @param choice Must be null as it is not used.
	 */
	@Override
	public void doTurn(Object choice) {
		
		if (choice != null) {
			
			throw new IllegalArgumentException("choice should be null for DiceRolls game.");
			
		}
		
		// if the state is drawn, set it to playing instead
		if (state == MinigameState.DRAWN) {
			
			state = MinigameState.PLAYING;
			
		}
		
		if (state == MinigameState.PLAYING) {
			
			heroRoll = rollDice();
			villainRoll = rollDice();
			
			// Improve the odds of the hero winning.
			// Take one away from the villains dice roll.
			// If that doesn't work, add one to the hero's dice roll.
			// Make sure that the resulting numbers are actual dice numbers.
			if (hasAbility(HeroAbility.IMPROVED_ODDS)) {
				
				villainRoll -= 1;
				
				if (villainRoll < 1) {
					villainRoll = 1;
					
					heroRoll += 1;
					
					if (heroRoll > 6) {
						heroRoll = 6;
					}
				}
				
			}
			
			// The hero has won if they rolled higher than the villain
			if (heroRoll > villainRoll) {
				
				state = MinigameState.WON;
				
			} else {
				
				if (heroRoll == villainRoll) {
					
					// If the hero rolls the same as the villain and they have the WIN_ON_DRAW ability, the hero wins.
					if (hasAbility(ItemAbility.WIN_ON_DRAW)) {
						
						state = MinigameState.WON;
						
					} else {
						
						state = MinigameState.DRAWN;
						
					}
					
				} else {
					
					state = MinigameState.LOST;
					
				}
				
			}
			
			
		} else {
			
			// Can't call doTurn on a game that has already finished.
			throw new Error("Can't call doTurn on a game that has already finished.");
			
		}

	}

	/**
	 * Returns the number the hero rolled.
	 */
	@Override
	public Integer getHeroLastTurn() {
		return heroRoll;
	}

	/**
	 * Returns the number the villain rolled.
	 */
	@Override
	public Integer getVillainLastTurn() {
		return villainRoll;
	}

	/**
	 * Returns the number of turns remaining.
	 */
	@Override
	public int getRemainingTurns() {
		
		if (state == MinigameState.PLAYING || state == MinigameState.DRAWN) {
			
			return 1;
			
		} else {
			
			return 0;
			
		}
	}

	/**
	 * Returns MinigameType.DICE_ROLLS.
	 */
	@Override
	public MinigameType getType() {
		return MinigameType.DICE_ROLLS;
	}
	
	/**
	 * An isolated test of the DiceRolls minigame.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Ability[] abilities = new Ability[] {
			// HeroAbility.IMPROVED_ODDS,
			// ItemAbility.WIN_ON_DRAW
		};
		
		DiceRolls game = new DiceRolls(abilities);
		
		game.doTurn(null);
		
		System.out.println(String.format("Your Hero rolled a %d!", (int)game.getHeroLastTurn()));
		System.out.println(String.format("The Villain rolled a %d!", (int)game.getVillainLastTurn()));
		System.out.println(String.format("The game was %s!", game.getState().toString().toLowerCase()));
		
	}

}
