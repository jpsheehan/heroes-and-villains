
public abstract class Minigame {

	public abstract void initialise(Ability[] abilities);
	
	public abstract MinigameState getState();
	
	public abstract void doTurn(Object choice);
	
	public abstract Object getHeroLastTurn();
	
	public abstract Object getVillainLastTurn();
	
	public abstract int getRemainingTurns();
	
	public abstract MinigameType getType();
	
}
