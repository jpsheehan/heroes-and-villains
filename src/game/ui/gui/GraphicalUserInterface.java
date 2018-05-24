package game.ui.gui;

import java.awt.EventQueue;

import game.ui.UserInterface;
import game.ui.gui.windows.GameWindow;

//TODO
/**
 * 
 * @author jesse
 *
 */
public class GraphicalUserInterface extends UserInterface {
	
	private boolean debugMode;
	
	public GraphicalUserInterface() {
		debugMode = false;
	}
	
	public GraphicalUserInterface(boolean debugMode)  {
		this.debugMode = debugMode;
	}

	@Override
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(debugMode);
					window.show();
				} catch (Exception e) {
					throw e;
				}
			}
		});
		
	}

}
