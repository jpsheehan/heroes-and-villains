package game;

public abstract class Minigame {
	
	private Ability[] abilities;

	public Minigame(Ability[] abilities) {
		this.abilities = abilities;
	}
	
	public boolean hasAbility(Ability ability) {
		
		for (Ability ourAbility : abilities) {
			
			if (ability.equals(ourAbility)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public abstract MinigameState getState();
	
	public abstract void doTurn(Object choice);
	
	public abstract Object getHeroLastTurn();
	
	public abstract Object getVillainLastTurn();
	
	public abstract int getRemainingTurns();
	
	public abstract MinigameType getType();
	
}
