package game.ui.gui;

import java.awt.EventQueue;

import game.ui.UserInterface;
import game.ui.gui.windows.GameWindow;

public class GraphicalUserInterface extends UserInterface {

	@Override
	public void start() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.show();
				} catch (Exception e) {
					throw e;
				}
			}
		});
		
	}

}
