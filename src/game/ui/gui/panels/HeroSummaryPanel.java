package game.ui.gui.panels;

import javax.swing.JPanel;

import game.character.Hero;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class HeroSummaryPanel extends JPanel {

	private Hero hero;
	
	private JLabel lblHeroName, lblHeroHealth;
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
		
		lblHeroName = new JLabel("<lblHeroName>");
		add(lblHeroName);
		
		healthBar = new JProgressBar();
		add(healthBar);
		
		lblHeroHealth = new JLabel("<lblHeroHealth>");
		add(lblHeroHealth);
		
		btnMoreInfo = new JButton("More Info...");
		add(btnMoreInfo);
		
		update();

	}
	

	public void update() {
		
		if (this.hero == null) {
			
			lblHeroName.setText("NULL");
			lblHeroHealth.setText("0/0");
			btnMoreInfo.setEnabled(false);
			healthBar.setIndeterminate(true);
			
		} else {
			
			lblHeroName.setText(hero.getName());
			lblHeroHealth.setText(String.format("%d/%d", hero.getHealth(), 100));
			healthBar.setValue(hero.getHealth());
			healthBar.setIndeterminate(false);
			btnMoreInfo.setEnabled(true);
			
		}
		
	}
}
