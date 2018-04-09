package game;

public abstract class Minigame {
	
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
	
	public abstract void doTurn(Object choice);
	
	public abstract Object getHeroLastTurn();
	
	public abstract Object getVillainLastTurn();
	
	public abstract int getRemainingTurns();
	
	public abstract MinigameType getType();
	
}
