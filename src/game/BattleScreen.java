package game;

import game.character.Hero;
import game.character.HeroAbility;
import game.character.Villain;
import game.minigame.Minigame;
import game.minigame.MinigameType;
import game.GeneralHelpers;

public class BattleScreen {

	/**
	 * The Villain being battled.
	 */
	private Villain villain;
	
	
	/**
	 * The index of the current city (used in money/damage calulcations)
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
	private Minigame minigame;
	
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
		
		return GeneralHelpers.getRandom().nextInt(100) * this.cityIndex;
		
	}
	
	/**
	 * Returns the damage dealt to the hero.
	 * @return
	 */
	private int calculateDamage() {
		
		float damageMultiplier = 1.0f;
		
		if (hero.getAbility() == HeroAbility.VILLAINS_LESS_20_HEALTH) {
			
			damageMultiplier = 0.8f;
			
		}
		
		return (int)(GeneralHelpers.getRandom().nextInt(10) * this.cityIndex * damageMultiplier);
		
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
	private void setMinigame() {
		
		// TODO: Finish
		
	}
	
	/**
	 * Sets the minigame explicitly.
	 * @param type
	 */
	private void setMinigame(MinigameType type) {
		
		// TODO: Finish
		
	}
	
	/**
	 * Returns the minigame being played.
	 * @return
	 */
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
	
	
}
