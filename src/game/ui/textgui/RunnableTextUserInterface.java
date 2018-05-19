package game.ui.textgui;

import game.GeneralHelpers;

public class RunnableTextUserInterface implements Runnable {

	private Thread thread;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		GeneralHelpers.setIsRunningInEclipse(false);
		start();
	}
	
	public void start() {
		if (this.thread == null) {
			this.thread = new Thread(this, "TextGui Thread");
			this.thread.start();
		}
	}
	
	public boolean isAlive() {
		return this.thread.isAlive();
	}
}
