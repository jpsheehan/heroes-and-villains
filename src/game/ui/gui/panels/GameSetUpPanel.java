package game.ui.gui.panels;

import javax.swing.JPanel;

import game.GameEnvironment;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;

public class GameSetUpPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6625880562455508713L;

	private GameEventListener parent;
	
	/**
	 * Create the panel.
	 */
	public GameSetUpPanel(GameEventListener parent) {

		this.parent = parent;
		
	}
	
	private void beginGame(GameEnvironment env) {
		
		parent.gameEventPerformed(new GameEvent(GameEventType.START_GAME, env));
		
	}

}
