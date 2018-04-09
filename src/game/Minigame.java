package game;

/**
 * A parent class of all the games.
 * @author jesse
 *
 * @param <S> The argument type passed in to the doTurn method.
 * @param <T> The return type of the getHeroLastTurn and getVillainLastTurn methods.
 */
public abstract class Minigame<S, T> {
	
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
	
	public abstract void doTurn(S choice);
	
	public abstract T getHeroLastTurn();
	
	public abstract T getVillainLastTurn();
	
	public abstract int getRemainingTurns();
	
	public abstract MinigameType getType();
	
}
