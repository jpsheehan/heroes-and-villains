package game.ui.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicProgressBarUI;

import game.character.Hero;

public class HeroHealthBar extends JProgressBar implements ActionListener {

	private static int UPDATE_DELAY = 200;
	private Hero hero;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7004751552534381921L;
	
	public HeroHealthBar(Hero hero) {
		
		super();
		
		this.hero = hero;
		
		setStringPainted(true);
		
		setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.white; }
		    protected Color getSelectionForeground() { return Color.black; }
		});
		
		// Start a timer to update the healthbar automatically
		(new Timer(UPDATE_DELAY, this)).start();
		
		update();
	}
	
	public void update() {

		setBackground(Color.BLACK);
		
		this.setString(null);
		
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
	
	public void setHero(Hero hero) {
		
		this.hero = hero;
		
		update();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		update();
		
	}

}
