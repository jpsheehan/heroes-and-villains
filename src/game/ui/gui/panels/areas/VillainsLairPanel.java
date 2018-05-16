package game.ui.gui.panels.areas;

import game.Team;
import game.character.Hero;
import game.city.VillainsLair;
import game.ui.gui.DialogResult;
import game.ui.gui.Triggerable;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.panels.games.PanelDiceRollsGame;
import game.ui.gui.panels.games.PanelGuessTheNumberGame;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import game.ui.gui.components.VillainHealthBar;

public class VillainsLairPanel extends GenericAreaPanel {
	
	private Hero selectedHero;
	private JLabel lblSelectedHero;
	private JButton btnReadyToBattle, btnSelectAHero;
	private boolean isBattleActive = false;
	private JPanel gamePanel;
	private JSplitPane splitPane;
	private VillainHealthBar villainHealthBar;

	/**
	 * 
	 */
	private static final long serialVersionUID = -921176693627897514L;

	/**
	 * Create the panel.
	 */
	public VillainsLairPanel(Triggerable window, VillainsLair villainsLair, Team team) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel villainPanel = new JPanel();
		// add(villainPanel);
		villainPanel.setLayout(new BoxLayout(villainPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		villainPanel.add(panel);
		
		JLabel lblVillainName = new JLabel(villainsLair.getVillain().getName());
		panel.add(lblVillainName);
		
		JPanel panel_5 = new JPanel();
		villainPanel.add(panel_5);
		
		villainHealthBar = new VillainHealthBar(villainsLair.getVillain());
		panel_5.add(villainHealthBar);
		
		JPanel panel_3 = new JPanel();
		villainPanel.add(panel_3);
		
		JLabel lblTauntPhrase = new JLabel(villainsLair.getVillain().getTaunt());
		panel_3.add(lblTauntPhrase);
		
		JPanel panel_1 = new JPanel();
		villainPanel.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Select a Hero:");
		panel_1.add(lblNewLabel);
		
		lblSelectedHero = new JLabel("lblSelectedHero");
		panel_1.add(lblSelectedHero);
		
		JPanel panel_2 = new JPanel();
		villainPanel.add(panel_2);
		
		btnSelectAHero = new JButton("Select a Hero...");
		btnSelectAHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HeroSelectionDialog dlg = new HeroSelectionDialog(team.getHeroes());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					selectedHero = dlg.getSelectedHero();
					update();
					
				}
				
			}
		});
		panel_2.add(btnSelectAHero);
		
		JPanel panel_4 = new JPanel();
		villainPanel.add(panel_4);
		
		btnReadyToBattle = new JButton("Ready to Battle!");
		btnReadyToBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isBattleActive = true;
				villainsLair.getBattleScreen().setHero(selectedHero);
				villainsLair.getBattleScreen().setMinigame();
				
				// set the new game panel
				switch (villainsLair.getBattleScreen().getMinigameType()) {
				
					case DICE_ROLLS:
						gamePanel = new PanelDiceRollsGame(window, villainsLair.getBattleScreen());
						break;
						
					case GUESS_THE_NUMBER:
						gamePanel = new PanelGuessTheNumberGame(window, villainsLair.getBattleScreen());
						break;
						
					case PAPER_SCISSORS_ROCK:
						// gamePanel = new PaperScissorsRockGamePanel();
						gamePanel = new JPanel();
						break;
						
					default:
						gamePanel = new JPanel();
						break;
				
				}
				
				// add the new game panel
				splitPane.setRightComponent(gamePanel);

				update();
				
			}
			
		});
		
		btnReadyToBattle.setEnabled(false);
		panel_4.add(btnReadyToBattle);
		
		splitPane = new JSplitPane();
		add(splitPane);
		
		gamePanel = new JPanel();
		
		splitPane.setLeftComponent(villainPanel);
		splitPane.setRightComponent(gamePanel);
		
		
	}

	@Override
	public void update() {
		
		if (selectedHero == null) {
			
			lblSelectedHero.setText("None");
			
		} else {
			
			lblSelectedHero.setText(selectedHero.getName());
			
		}
		
		btnSelectAHero.setEnabled(isBattleActive == false);
		btnReadyToBattle.setEnabled(selectedHero != null && isBattleActive == false);
		
	}

}
