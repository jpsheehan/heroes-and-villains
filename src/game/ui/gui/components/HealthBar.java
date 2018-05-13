package game.ui.gui.components;

import java.awt.Color;

import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import game.character.Hero;

public class HealthBar extends JProgressBar {

	private Hero hero;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7004751552534381921L;
	
	public HealthBar(Hero hero) {
		
		super();
		
		this.hero = hero;

		setBackground(Color.BLACK);
		setStringPainted(true);
		setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.white; }
		    protected Color getSelectionForeground() { return Color.black; }
		});
		
	}
	
	public void update() {
		
		if (hero == null) {
			
			setIndeterminate(true);
			
		} else {
			
			setIndeterminate(false);
			
			setMaximum(hero.getMaxHealth());
			setValue(hero.getHealth());
			
			// update the color of the healthBar
			if (hero.getHealth() > 70) {
				
				setForeground(Color.GREEN);
				
			} else if (hero.getHealth() > 40) {
				
				setForeground(Color.YELLOW);
				
			} else if (hero.getHealth() > 10) {
				
				setForeground(Color.ORANGE);
				
			} else if (hero.getHealth() > 0) {
				
				setForeground(Color.RED);
				
			} else {
				
				setBackground(Color.GRAY);
				setString("Dead");
				
			}
			
		}
		
		
	}

}
