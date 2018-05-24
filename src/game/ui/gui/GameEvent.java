package game.ui.gui;

//TODO
/**
 * 
 * @author jesse
 *
 */
public class GameEvent {

	private GameEventType type;
	private Object param;
	
	public GameEvent(GameEventType type) {
		
		this.type = type;
		this.param = null;
		
	}
	
	public GameEvent(GameEventType type, Object param) {
		
		this.type = type;
		this.param = param;
		
	}
	
	public GameEventType getType() {
		
		return this.type;
		
	}
	
	public Object getParameters() {
		
		return this.param;
		
	}
	
}
