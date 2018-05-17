package game.ui.gui;

public class GameEvent {

	private GameEventType type;
	private Object[] params;
	
	public GameEvent(GameEventType type, Object[] ...params) {
		
		this.type = type;
		this.params = params;
		
	}
	
	public GameEventType getType() {
		
		return this.type;
		
	}
	
	public Object[] getParameters() {
		
		return this.params;
		
	}
	
}
