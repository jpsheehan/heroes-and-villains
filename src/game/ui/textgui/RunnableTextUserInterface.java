package game.ui.textgui;

import game.GameEnvironment;
import game.GeneralHelpers;
import game.ui.text.TextUserInterface;

public class RunnableTextUserInterface implements Runnable {

	private Thread thread;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		GeneralHelpers.setIsRunningInEclipse(false);
		new GameEnvironment(TextUserInterface.class).run();
	}
	
	public void start() {
		if (this.thread == null) {
			this.thread = new Thread(this, "TextGui Thread");
			this.thread.start();
		}
	}
}
