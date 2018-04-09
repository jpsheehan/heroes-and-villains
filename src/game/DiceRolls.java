package game;

import java.util.Random;

public class DiceRolls extends Minigame {
	
	private int heroRoll;
	private int villainRoll;
	
	public DiceRolls(Ability[] abilities) {
		super(abilities);
	}

	/**
	 * Returns the result of a fair dice roll.
	 * @return
	 */
	private int rollDice() {

		Random rand = new Random();
		return rand.nextInt(6) + 1;
		
	}
	
	@Override
	public void doTurn(Object choice) {
		
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

	@Override
	public Object getHeroLastTurn() {
		return heroRoll;
	}

	@Override
	public Object getVillainLastTurn() {
		return villainRoll;
	}

	@Override
	public int getRemainingTurns() {
		return 0;
	}

	@Override
	public MinigameType getType() {
		return MinigameType.DICE_ROLLS;
	}
	
	public static void main(String[] args) {
		
		// Add the abilities that you want to start the game with here:
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
