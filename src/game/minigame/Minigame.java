package game.minigame;

import java.io.Serializable;

import game.Ability;

/**
 * A parent class of all the games.
 * @author jesse
 *
 * @param <C> The argument type passed in to the doTurn method.
 * @param <H> The return type of the getHeroLastTurn method.
 * @param <V> The return type of the getVillainLastTurn method.
 */
public abstract class Minigame<C, H, V> implements Serializable {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -98943080066864109L;
	private Ability[] abilities;
	protected MinigameState state;

	public Minigame(Ability[] abilities) {
		this.abilities = abilities;
		state = MinigameState.PLAYING;
	}
	
	public final boolean hasAbility(Ability ability) {
		
		for (Ability ourAbility : abilities) {
			
			if (ability.equals(ourAbility)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public final MinigameState getState()  {
		return this.state;
	}
	
	public abstract void doTurn(C choice);
	
	public abstract H getHeroLastTurn();
	
	public abstract V getVillainLastTurn();
	
	public abstract int getRemainingTurns();
	
	public abstract MinigameType getType();
	
}
