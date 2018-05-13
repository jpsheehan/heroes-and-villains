package game.ui.gui.panels;

import javax.swing.JPanel;

import game.character.Hero;
import game.ui.gui.components.HealthBar;
import game.ui.gui.dialogs.HeroInformationDialog;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeroSummaryPanel extends JPanel {

	private Hero hero;
	
	private JLabel lblHeroName;
	private HealthBar healthBar;
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
		
		healthBar = new HealthBar(this.hero);
		add(healthBar);
		
		btnMoreInfo = new JButton("More Info...");
		btnMoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new HeroInformationDialog(hero)).setVisible(true);
			}
		});
		add(btnMoreInfo);
		
		update();

	}
	

	public void update() {
		
		if (this.hero == null) {
			
			lblHeroName.setText("NULL");
			btnMoreInfo.setEnabled(false);
			
		} else {
			
			lblHeroName.setText(hero.getName());
			btnMoreInfo.setEnabled(true);
			
			if (!hero.isAlive() ) {
				
				lblHeroName.setForeground(Color.GRAY);
				btnMoreInfo.setEnabled(false);
				
			}
		}
		
		healthBar.update();
		
	}
	
}
