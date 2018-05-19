package game.ui.textgui;

import game.ui.UserInterface;
import game.ui.textgui.ConsoleWrapperWindow;

public class TextGraphicalUserInterface extends UserInterface {

	public TextGraphicalUserInterface() {
		super();
	}

	@Override
	public void start() {
		ConsoleWrapperWindow.main(null);
	}

}
