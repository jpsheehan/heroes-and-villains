package game;

import game.character.Hero;
import game.character.HeroAbility;
import game.character.HeroDeadException;
import game.character.HeroType;
import game.character.Villain;
import game.character.VillainDeadException;
import game.item.ItemAbility;
import game.minigame.DiceRolls;
import game.minigame.GuessTheNumber;
import game.minigame.Minigame;
import game.minigame.MinigameType;
import game.minigame.PaperScissorsRock;

import java.io.Serializable;
import java.util.ArrayList;

import game.GeneralHelpers;

public class BattleScreen implements Serializable {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -8383399144385457589L;


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
	 * A reference to the team.
	 */
	private Team team;
	
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
	 * The type of minigame to force (for debug menu).
	 */
	private static MinigameType forcedType;
	
	/**
	 * Creates a new BattleScreen.
	 * @param villain The villain the hero is to battle.
	 * @param cityIndex The index of the city the team is in.
	 */
	public BattleScreen(Villain villain) {
		
		this.villain = villain;
		this.cityIndex = 0;
		this.villainHealth = DEFAULT_VILLAIN_HEALTH;
		this.team = null;
		
	}
	
	public void setCityIndex(int cityIndex) {
		
		this.cityIndex = cityIndex;
		
	}
	
	public void setTeam(Team team) {
		
		this.team = team;
		
	}
	
	/**
	 * Returns the money reward for winning this battle.
	 * @return
	 */
	private int calculateMoneyReward() {
		
		float rewardMultiplier = 1.0f;
		
		for (Hero hero : team.getHeroes()) {
			if (hero.getType() == HeroType.COMMERCE_STUDENT) {
				rewardMultiplier *= 1.5f;
			}
		}
		
		return (int)(GeneralHelpers.max(5, GeneralHelpers.getRandom().nextInt(10)) * rewardMultiplier * (this.cityIndex + 1));
		
	}
	
	/**
	 * Returns the damage dealt to the hero.
	 * @return
	 */
	private int calculateDamage() {
		
		float damageMultiplier = 1.0f;
		
		if (hero.getAbility() == HeroAbility.DAMAGE_REDUCTION) {
			
			damageMultiplier = 0.5f;
			
		}
		
		if (hero.getIsPowerUpItemActive() && hero.getPowerUpItem().getAbility() == ItemAbility.DAMAGE_PROTECTION) {
			
			hero.destroyPowerUpItem();
			return 0;
			
		}
		
		return (int)(GeneralHelpers.max(2, GeneralHelpers.getRandom().nextInt(10)) * (this.cityIndex + 1) * damageMultiplier);
		
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
		
		MinigameType type = this.villain.getFavouriteGame();
		
		if (type == MinigameType.ALL) {
			
			MinigameType[] possibleTypes = new MinigameType[] {
				MinigameType.DICE_ROLLS,
				MinigameType.GUESS_THE_NUMBER,
				MinigameType.PAPER_SCISSORS_ROCK
			};
			
			type = possibleTypes[GeneralHelpers.getRandom().nextInt(possibleTypes.length)];
			
		}
		
		if (forcedType != null) {
			type = forcedType;
		}
		
		this.setMinigame(type);
		
	}
	
	/**
	 * Sets the minigame explicitly.
	 * @param type
	 */
	private void setMinigame(MinigameType type) {
		
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
			
			abilities.add((Ability)this.hero.getPowerUpItem().getAbility());
			
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
		
		try {
			switch (this.minigame.getState()) {
			
				case WON:
					// Deal "damage" to the villain and check if they are dead.
					this.villain.beat();
					
					if (this.villain.getNumberOfWinsToDefeat() == 0) {
						
						throw new VillainDeadException(this.villain, calculateMoneyReward());
					}
	
					this.setMinigame();
					break;
					
				case LOST:
					// Deal damage to the hero and begin a new game.
					this.hero.takeDamage(this.calculateDamage());
					this.setMinigame();
					break;
					
				case DRAWN:
					
					// Play the same game again.
					this.setMinigame(this.getMinigameType());
					break;
					
				default:
					throw new AssertionError();
					
			}
		} catch (VillainDeadException e) {
			
			this.destroyAllActiveHeroItems();
			throw e;
			
		}
		
	}
	
	/**
	 * Destroys the hero's power up item if it is being used in the current game.
	 * To be called after the game is over.
	 */
	private void destroyAllActiveHeroItems() {
		
		for (Hero hero : team.getHeroes()) {
			
			hero.destroyPowerUpItem();
			
		}
		
	}
	
	/**
	 * Forces the minigame type across all Villain's Lairs.
	 * @param type The type of minigame to force.
	 */
	public static void forceMinigameType(MinigameType type) {
		forcedType = type;
	}
	
}
