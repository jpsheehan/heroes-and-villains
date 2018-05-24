package game.ui.gui.panels;

import game.character.Hero;
import game.ui.gui.components.HeroHealthBar;
import game.ui.gui.dialogs.HeroInformationDialog;
import game.ui.gui.panels.areas.GenericAreaPanel;
import game.ui.gui.windows.GameWindow;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HeroSummaryPanel extends GenericAreaPanel {

	private Hero hero;
	
	private JLabel lblHeroName;
	private HeroHealthBar healthBar;
	private JButton btnMoreInfo;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 7510751669586968290L;

	/**
	 * Create the panel.
	 */
	public HeroSummaryPanel(Hero hero) {
		
		this.hero = hero;
		setLayout(new GridLayout(0, 3, 0, 0));
		
		lblHeroName = new JLabel();
		lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblHeroName);
		
		healthBar = new HeroHealthBar(this.hero);
		add(healthBar);
		
		btnMoreInfo = new JButton("More Info");
		btnMoreInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HeroInformationDialog dlg = new HeroInformationDialog(hero);
				dlg.setLocationRelativeTo(GameWindow.getMainWindow());
				dlg.setVisible(true);
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
