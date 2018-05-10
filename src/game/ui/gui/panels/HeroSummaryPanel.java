package game.ui.gui.panels;

import javax.swing.JPanel;

import game.character.Hero;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class HeroSummaryPanel extends JPanel {

	private Hero hero;
	
	private JLabel lblHeroName;
	private JProgressBar healthBar;
	private JButton btnMoreInfo;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7510751669586968290L;

	/**
	 * Create the panel.
	 */
	public HeroSummaryPanel(Hero hero) {
		
		this.hero = hero;
		setLayout(new GridLayout(0, 3, 0, 0));
		
		lblHeroName = new JLabel("<lblHeroName>");
		lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHeroName);
		
		healthBar = new JProgressBar();
		healthBar.setBackground(Color.BLACK);
		healthBar.setStringPainted(true);
		healthBar.setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.white; }
		    protected Color getSelectionForeground() { return Color.black; }
		});
		add(healthBar);
		
		btnMoreInfo = new JButton("More Info...");
		add(btnMoreInfo);
		
		update();

	}
	

	public void update() {
		
		if (this.hero == null) {
			
			lblHeroName.setText("NULL");
			btnMoreInfo.setEnabled(false);
			// healthBar.setString("?/?");
			healthBar.setIndeterminate(true);
			
		} else {
			
			lblHeroName.setText(hero.getName());
			// healthBar.setString(String.format("%d/%d", hero.getHealth(), 100));
			healthBar.setValue(hero.getHealth());
			healthBar.setIndeterminate(false);
			btnMoreInfo.setEnabled(true);
			
		}
		
		// update the color of the healthBar
		if (hero.getHealth() > 70) {
			
			healthBar.setForeground(Color.GREEN);
			
		} else if (hero.getHealth() > 40) {
			
			healthBar.setForeground(Color.YELLOW);
			
		} else if (hero.getHealth() > 10) {
			
			healthBar.setForeground(Color.ORANGE);
			
		} else if (hero.getHealth() > 0) {
			
			healthBar.setForeground(Color.RED);
			
		} else {
			
			healthBar.setBackground(Color.GRAY);
			healthBar.setString("Dead");
			lblHeroName.setForeground(Color.GRAY);
			btnMoreInfo.setEnabled(false);
			
		}
		
	}
}
