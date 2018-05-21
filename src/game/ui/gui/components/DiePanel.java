package game.ui.gui.components;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DiePanel extends JPanel implements ActionListener {
	
	private static int delay = 100;
	private static int iterations = 20;
	
	private int finalNumber;
	private int currentNumber;
	private int iteration;
	private boolean isRolling;
	private Random rng;
	private Timer timer;
	private GameEventListener parent;

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 2335984350090502421L;

	/**
	 * Create the panel.
	 */
	public DiePanel(GameEventListener parent) {
		
		Component rigidArea = Box.createRigidArea(new Dimension(64, 64));
		add(rigidArea);
		
		this.parent = parent;
		
		// Make a new random number generator. don't pollute the global one for the sake of an animation
		rng = new Random();
		currentNumber = generateRandomNumber();
		
		isRolling = false;
		timer = new Timer(delay, this);

	}
	
	private int generateRandomNumber() {
		
		return rng.nextInt(6) + 1;
		
	}
	
	public void roll(int finalNumber) {
		
		if (isRolling) {
			
			throw new AssertionError("DiePanel is already rolling!");
			
		} else {
			
			this.finalNumber = finalNumber;
			this.isRolling = true;
			this.iteration = 0;
			
			timer.start();
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		int radius = getWidth() / 10;
		
		// draw the background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// draw a border
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		
		switch (this.currentNumber) {
		
			case 5:
				drawPip(g, getWidth() / 4, getHeight() / 4, radius); // top-left pip
				drawPip(g, 3 * getWidth() / 4, 3 * getHeight() / 4, radius); // bottom-right pip
			case 3:
				drawPip(g, getWidth() / 4, 3 * getHeight() / 4, radius); // bottom-left pip
				drawPip(g, 3 * getWidth() / 4, getHeight() / 4, radius); // top-right pip
			case 1:
				drawPip(g, getWidth() / 2, getHeight() / 2, radius); // centre pip
				break;
				
			case 6:
				drawPip(g, getWidth() / 4, getHeight() / 2, radius); // middle-left pip
				drawPip(g, 3 * getWidth() / 4, getHeight() / 2, radius); // middle-right pip
			case 4:
				drawPip(g, getWidth() / 4, getHeight() / 4, radius); // top-left pip
				drawPip(g, 3 * getWidth() / 4, 3 * getHeight() / 4, radius); // bottom-right pip
			case 2:
				drawPip(g, getWidth() / 4, 3 * getHeight() / 4, radius); // bottom-left pip
				drawPip(g, 3 * getWidth() / 4, getHeight() / 4, radius); // top-right pip
				break;
			
			default:
				throw new AssertionError("Invalid die roll.");
		}
		
	}
	
	/**
	 * Draws a pip centred about x, y with a radius of 5 
	 * @param x
	 * @param y
	 */
	private void drawPip(Graphics g, int x, int y, int radius) {
		
		g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (isRolling) {
			
			currentNumber = generateRandomNumber();
			
			if (iteration++ >= iterations) {
				
				isRolling = false;
				
			}
			
		} else {
			
			timer.stop();
			currentNumber = finalNumber;
			parent.gameEventPerformed(new GameEvent(GameEventType.ANIMATION_COMPLETE));
			
		}
		
		repaint();
		
	}

}
