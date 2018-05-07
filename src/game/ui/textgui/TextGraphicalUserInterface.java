package game.ui.textgui;

import game.GameEnvironment;
import game.ui.UserInterface;
import game.ui.textgui.ConsoleWrapperWindow;

public class TextGraphicalUserInterface extends UserInterface {

	public TextGraphicalUserInterface(GameEnvironment env) {
		super(env);
	}

	@Override
	public void start() {
		ConsoleWrapperWindow.main(null);
	}

}
