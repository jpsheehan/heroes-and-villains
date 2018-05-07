package game.ui.gui;

import game.GameEnvironment;
import game.ui.UserInterface;
import game.ui.gui.windows.GameWindow;

public class GraphicalUserInterface extends UserInterface {

	public GraphicalUserInterface(GameEnvironment env) {
		super(env);
	}

	@Override
	public void start() {
		GameWindow.main(null);
	}

}
