package game.ui.gui;

import java.util.ArrayList;

public class KonamiQueue {

	private ArrayList<Integer> queue;
	
	private final static int UP = 38;
	private final static int DOWN = 40;
	private final static int LEFT = 37;
	private final static int RIGHT = 39;
	private final static int B = 66;
	private final static int A = 65;
	private final static int ENTER = 10;
	
	private static int[] konamiCode = new int[] { UP, UP, DOWN, DOWN, LEFT, RIGHT, LEFT, RIGHT, B, A, ENTER };
	
	public KonamiQueue() {
		
		queue = new ArrayList<Integer>();
		
	}
	
	
	public void clear() {
		
		queue.clear();
		
	}
	
	public void enqueue(int keyCode) {
		
		queue.add(keyCode);
		
	}
	
	public boolean getActivated() {
		
		while (queue.size() > konamiCode.length) {
			
			queue.remove(0);
			
		}
		
		if (queue.size() != konamiCode.length) {
			
			return false;
			
		}
		
		for (int i = 0; i < queue.size(); i++) {
			
			if (!queue.get(i).equals(konamiCode[i])) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
}
