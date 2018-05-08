package game.ui.gui;

import java.awt.EventQueue;

import game.GameEnvironment;
import game.ui.UserInterface;
import game.ui.gui.windows.GameWindow;

public class GraphicalUserInterface extends UserInterface {

	public GraphicalUserInterface(GameEnvironment env) {
		super(env);
	}

	@Override
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(getGameEnvironment());
					window.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
