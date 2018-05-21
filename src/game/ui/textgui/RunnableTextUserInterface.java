package game.ui.textgui;

public class RunnableTextUserInterface implements Runnable {

	private Thread thread;
	
	@Override
	public void run() {
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
