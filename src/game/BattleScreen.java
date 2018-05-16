package game;

import game.character.Hero;
import game.character.HeroAbility;
import game.character.HeroDeadException;
import game.character.Villain;
import game.character.VillainDeadException;
import game.minigame.DiceRolls;
import game.minigame.GuessTheNumber;
import game.minigame.Minigame;
import game.minigame.MinigameType;
import game.minigame.PaperScissorsRock;

import java.util.ArrayList;

import game.GeneralHelpers;

public class BattleScreen {

	/**
	 * The Villain being battled.
	 */
	private Villain villain;
	
	
	/**
	 * The index of the current city (used in money/damage calculations)
	 */
	private int cityIndex;
	
	/**
	 * The hero that is currently battling.
	 */
	private Hero hero;
	
	/**
	 * The current health of the villain
	 */
	private int villainHealth;
	
	/**
	 * The number of times villain must be beaten.
	 */
	private static int DEFAULT_VILLAIN_HEALTH = 3;
	
	/**
	 * The minigame being played.
	 */
	@SuppressWarnings("rawtypes")
	private Minigame minigame;
	
	/**
	 * Creates a new BattleScreen.
	 * @param villain The villain the hero is to battle.
	 * @param cityIndex The index of the city the team is in.
	 */
	public BattleScreen(Villain villain, int cityIndex) {
		
		this.villain = villain;
		this.cityIndex = cityIndex;
		this.villainHealth = DEFAULT_VILLAIN_HEALTH;
		
	}
	
	/**
	 * Returns the money reward for winning this battle.
	 * @return
	 */
	private int calculateMoneyReward() {
		
		float rewardMultiplier = 1.0f;
		
		// Consider adding an ability that grants more money.
		
		return (int)(GeneralHelpers.getRandom().nextInt(100) * rewardMultiplier * (this.cityIndex + 1));
		
	}
	
	/**
	 * Returns the damage dealt to the hero.
	 * @return
	 */
	private int calculateDamage() {
		
		float damageMultiplier = 1.0f;
		
		if (hero.getAbility() == HeroAbility.DAMAGE_REDUCTION) {
			
			damageMultiplier = 0.8f;
			
		}
		
		return (int)(GeneralHelpers.getRandom().nextInt(10) * (this.cityIndex + 1) * damageMultiplier);
		
	}
	
	/**
	 * Returns the Hero that is currently battling.
	 * @return
	 */
	public Hero getHero() {
		
		return this.hero;
		
	}
	
	/**
	 * Sets the Hero that is currently battling.
	 * @param hero
	 */
	public void setHero(Hero hero) {
		
		this.hero = hero;
		
	}
	
	/**
	 * Randomly sets the minigame from a list of the Villains favourite games.
	 */
	public void setMinigame() {
		
		MinigameType type = this.villain.getFavouriteGames()[
				GeneralHelpers.getRandom().nextInt(this.villain.getFavouriteGames().length)
		];
		
		if (type == MinigameType.ALL) {
			
			MinigameType[] possibleTypes = new MinigameType[] {
				MinigameType.DICE_ROLLS,
				MinigameType.GUESS_THE_NUMBER,
				MinigameType.PAPER_SCISSORS_ROCK
			};
			
			type = possibleTypes[GeneralHelpers.getRandom().nextInt(possibleTypes.length)];
			
		}
		
		this.setMinigame(type);
		
	}
	
	/**
	 * Sets the minigame explicitly.
	 * @param type
	 */
	private void setMinigame(MinigameType type) {
		
//		if (GeneralHelpers.doesArrayContain(this.villain.getFavouriteGames(), type) || this.villain.getFavouriteGames() ) {
			
			Ability[] abilities = this.calculateAbilities(type);
			
			switch (type) {
			
				case DICE_ROLLS:
					this.minigame = new DiceRolls(abilities);
					break;
				
				case GUESS_THE_NUMBER:
					this.minigame = new GuessTheNumber(abilities);
					break;
				
				case PAPER_SCISSORS_ROCK:
					this.minigame = new PaperScissorsRock(abilities);
					break;
				
				default:
					throw new AssertionError("MinigameType is invalid.");
					
			}
		
//		} else {
//		
//			throw new IllegalArgumentException("Villain does not have this minigame as one of their favourites.");
//			
//		}
		
	}
	
	
	/**
	 * Calculates the abilities from the Hero and Team, based on the type of minigame being run. 
	 * @return Returns an array of active abilities.
	 */
	private Ability[] calculateAbilities(MinigameType type) {
		
		ArrayList<Ability> abilities = new ArrayList<Ability>();
		
		if (this.hero.hasPowerUpItem() &&
				(this.hero.getPowerUpItem().getAppliesTo() == MinigameType.ALL ||
				this.hero.getPowerUpItem().getAppliesTo() == type)
			) {
			
			abilities.add((Ability)this.hero.getPowerUpItem());
			
		}
		
		abilities.add((Ability)this.hero.getAbility());
		
		Ability[] abilitiesArray = new Ability[abilities.size()];
		for (int i = 0; i < abilities.size(); i++) {
			
			abilitiesArray[i] = abilities.get(i);
			
		}
				
		return abilitiesArray;
		
	}
	
	/**
	 * Returns the minigame being played.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Minigame getMinigame() {
		
		return this.minigame;
		
	}
	
	/**
	 * Returns the type of minigame being played.
	 * @return
	 */
	public MinigameType getMinigameType() {
		
		return this.minigame.getType();
		
	}
	
	/**
	 * Returns the current villain health.
	 * @return
	 */
	public int getVillainHealth() {
		
		return this.villainHealth;
		
	}
	
	/**
	 * To be called after running getMinigame().doTurn(). Checks what actions should be taken depending on the outcome of the minigame.
	 * @throws VillainDeadException 
	 * @throws HeroDeadException 
	 */
	public void minigameStateChecker() throws VillainDeadException, HeroDeadException {
		
		switch (this.minigame.getState()) {
		
			case WON:
				// Deal "damage" to the villain and check if they are dead.
				this.villain.beat();
				
				if (this.villain.getTimesBeaten() == this.villain.getWinsToDefeat()) {
					
					throw new VillainDeadException(this.villain, calculateMoneyReward());
				}

				this.destroyHeroItem();
				this.setMinigame();
				break;
				
			case LOST:
				// Deal damage to the hero and begin a new game.
				this.hero.takeDamage(this.calculateDamage());
				this.destroyHeroItem();
				this.setMinigame();
				break;
				
			case DRAWN:
				
				// Play the same game again.
				// this.destroyHeroItem(); // don't destroy the power up if it's a draw??
				this.setMinigame(this.getMinigameType());
				break;
			
			default:
				// do nothing...
		}
		
	}
	
	/**
	 * Destroys the hero's power up item if it is being used in the current game.
	 * To be called after the game is over.
	 */
	private void destroyHeroItem() {
		
		if (
				hero != null &&
				hero.hasPowerUpItem() &&
				(hero.getPowerUpItem().getAppliesTo() == MinigameType.ALL ||
				hero.getPowerUpItem().getAppliesTo() == this.getMinigameType())) {
			
			this.hero.destroyPowerUpItem();
			
		}
		
	}
	
	
}
