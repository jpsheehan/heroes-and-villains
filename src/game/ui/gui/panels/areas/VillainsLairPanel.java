package game.ui.gui.panels.areas;

import game.GameEnvironment;
import game.character.Hero;
import game.character.HeroDeadException;
import game.character.VillainDeadException;
import game.city.VillainsLair;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.panels.games.DiceRollsGamePanel;
import game.ui.gui.panels.games.GuessTheNumberGamePanel;
import game.ui.gui.panels.games.PaperScissorsRockPanel;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import game.ui.gui.components.VillainHealthBar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;

public class VillainsLairPanel extends GenericAreaPanel implements GameEventListener, ActionListener {
	
	private Hero selectedHero;
	private JLabel lblSelectedHero;
	private JButton btnReadyToBattle, btnSelectAHero;
	private boolean isBattleActive = false;
	private JPanel gamePanel;
	private JSplitPane splitPane;
	private VillainHealthBar villainHealthBar;
	private GameEventListener window;
	private GameEventListener self;
	private JLabel lblMessage, lblInformation;
	private VillainsLair villainsLair;
	private JButton btnGoToNextBuilding, btnActivatePowerUp;
	private GameEnvironment gameEnvironment;
	private JLabel lblPowerUp;
	private Timer showDialogTimer;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -921176693627897514L;

	/**
	 * Create the panel.
	 */
	public VillainsLairPanel(GameEventListener window, VillainsLair villainsLair, GameEnvironment gameEnvironment) {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.window = window;
		this.villainsLair = villainsLair;
		this.gameEnvironment = gameEnvironment;
		
		this.villainsLair.getBattleScreen().setTeam(gameEnvironment.getTeam());
		
		// An alias of this
		this.self = this;
		
		JPanel villainPanel = new JPanel();
		villainPanel.setLayout(new BoxLayout(villainPanel, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		villainPanel.add(panel);
		
		JLabel lblVillainName = new JLabel("Villain Name: " + villainsLair.getVillain().getName());
		panel.add(lblVillainName);
		
		JPanel panel_5 = new JPanel();
		villainPanel.add(panel_5);
		
		villainHealthBar = new VillainHealthBar(villainsLair.getVillain());
		panel_5.add(villainHealthBar);
		
		JPanel panel_3 = new JPanel();
		villainPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTauntPhrase = new JLabel("<html>\"" + villainsLair.getVillain().getTaunt() + "\"</html>");
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
				
				HeroSelectionDialog dlg = new HeroSelectionDialog(gameEnvironment.getTeam().getHeroes());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					selectedHero = dlg.getSelectedHero();
					update();
					
				}
				
			}
		});
		panel_2.add(btnSelectAHero);
		
		btnActivatePowerUp = new JButton("Activate Power Up!");
		btnActivatePowerUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				selectedHero.activatePowerUpItem();
				JOptionPane.showMessageDialog((Component)self, String.format("You have activated %s! %s", selectedHero.getPowerUpItem().getName(), selectedHero.getPowerUpItem().getAbility().getFlavourText()), "Success", JOptionPane.INFORMATION_MESSAGE);
				update();
				
			}
		});
		panel_2.add(btnActivatePowerUp);
		
		JPanel panel_8 = new JPanel();
		villainPanel.add(panel_8);
		
		lblPowerUp = new JLabel("lblPowerUp");
		lblPowerUp.setForeground(Color.MAGENTA);
		panel_8.add(lblPowerUp);
		
		JPanel panel_4 = new JPanel();
		villainPanel.add(panel_4);
		
		btnReadyToBattle = new JButton("Ready to Battle!");
		btnReadyToBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				isBattleActive = true;
				villainsLair.getBattleScreen().setHero(selectedHero);
				villainsLair.getBattleScreen().setMinigame();
				
				lblMessage.setText("");
				lblInformation.setText("");
				
				// set the new game panel
				switch (villainsLair.getBattleScreen().getMinigameType()) {
				
					case DICE_ROLLS:
						gamePanel = new DiceRollsGamePanel(self, villainsLair.getBattleScreen());
						break;
						
					case GUESS_THE_NUMBER:
						gamePanel = new GuessTheNumberGamePanel(self, villainsLair.getBattleScreen());
						break;
						
					case PAPER_SCISSORS_ROCK:
						gamePanel = new PaperScissorsRockPanel(self, villainsLair.getBattleScreen());
						break;
						
					default:
						gamePanel = new JPanel();
						break;
				
				}
				
				// add the new game panel
				splitPane.setRightComponent(gamePanel);
				splitPane.setDividerLocation(0.5);

				update();
				
			}
			
		});
		
		btnReadyToBattle.setEnabled(false);
		panel_4.add(btnReadyToBattle);
		
		btnGoToNextBuilding = new JButton("Go To Next Building!");
		btnGoToNextBuilding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				window.gameEventPerformed(new GameEvent(GameEventType.GO_TO_NEXT_CITY));
				
			}
		});
		btnGoToNextBuilding.setEnabled(false);
		btnGoToNextBuilding.setVisible(false);
		panel_4.add(btnGoToNextBuilding);
		
		splitPane = new JSplitPane();
		splitPane.setContinuousLayout(true);
		add(splitPane);
		
		gamePanel = new JPanel();
		
		splitPane.setLeftComponent(villainPanel);
		
		JPanel panel_6 = new JPanel();
		villainPanel.add(panel_6);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 14));
		panel_6.add(lblMessage);
		
		JPanel panel_7 = new JPanel();
		villainPanel.add(panel_7);
		
		lblInformation = new JLabel("");
		panel_7.add(lblInformation);
		splitPane.setRightComponent(gamePanel);
		splitPane.setDividerLocation(0.5);
		
		showDialogTimer = new Timer(100, this);
		showDialogTimer.start();
		
	}

	@Override
	public void update() {
		
		if (selectedHero == null) {
			
			lblSelectedHero.setText("None");
			btnActivatePowerUp.setEnabled(false);
			
		} else {
			
			lblSelectedHero.setText(selectedHero.getName());
			btnActivatePowerUp.setEnabled(!selectedHero.getIsPowerUpItemActive() && selectedHero.hasPowerUpItem());
			
		}
		
		if (selectedHero != null && selectedHero.getIsPowerUpItemActive()) {
			
			lblPowerUp.setText(selectedHero.getPowerUpItem().getName());
			lblPowerUp.setVisible(true);
			
		} else {
			
			lblPowerUp.setVisible(false);
			
		}
		
		btnSelectAHero.setEnabled(isBattleActive == false);
		btnReadyToBattle.setEnabled(selectedHero != null && isBattleActive == false);
		
		villainHealthBar.update();
		
	}

	@Override
	public void gameEventPerformed(GameEvent event) {
		
		switch (event.getType()) {
		
			case MINIGAME_WON:

				isBattleActive = false;

				lblMessage.setText("YOU WON!");
				lblMessage.setForeground(Color.GREEN);
				
				try {
					
					villainsLair.getBattleScreen().minigameStateChecker();

					update();
					
				} catch (VillainDeadException e) {

					lblInformation.setText(String.format("%s has been defeated! You won $%d!", e.getVillain().getName(), e.getReward()));
					
					if (gameEnvironment.getCityController().isLastCity()) {
						btnGoToNextBuilding.setText("Graduate!");
					}
					
					btnGoToNextBuilding.setEnabled(true);
					btnGoToNextBuilding.setVisible(true);
					
					btnReadyToBattle.setEnabled(false);
					btnReadyToBattle.setVisible(false);
					
					btnActivatePowerUp.setEnabled(false);
					btnActivatePowerUp.setVisible(false);
					
					btnSelectAHero.setEnabled(false);
					
					gameEnvironment.getTeam().giveMoney(e.getReward());
					
					window.gameEventPerformed(new GameEvent(GameEventType.TEAM_CHANGED));

					return;
					
				} catch (HeroDeadException e) {
					
					throw new AssertionError(e);
					
				}
				
				lblInformation.setText(String.format("You dealt some damage to %s!", villainsLair.getVillain().getName()));
				
				break;
				
			case MINIGAME_LOST:
				
				isBattleActive = false;

				lblMessage.setText("YOU LOST!");
				lblMessage.setForeground(Color.RED);
				
				try {
					
					villainsLair.getBattleScreen().minigameStateChecker();
					
					this.window.gameEventPerformed(new GameEvent(GameEventType.TEAM_CHANGED));
					
				} catch (HeroDeadException e) {
					
					lblInformation.setText(String.format("%s has died! Please select another hero.", e.getHero().getName()));
					selectedHero = null;
					update();
					
					if (gameEnvironment.getTeam().getNumberOfAliveHeroes() == 0) {
						
						this.window.gameEventPerformed(new GameEvent(GameEventType.GAME_LOST));
						
					}
					
					return;
					
				} catch (VillainDeadException e) {
					
					throw new AssertionError(e);
					
				}

				update();
				lblInformation.setText(String.format("%s dealt some damage to %s!", villainsLair.getVillain().getName(), selectedHero.getName()));
				
				break;
				
			case MINIGAME_DRAWN:
				
				// display a message saying that the game was drawn
				lblMessage.setText("YOU DREW!");
				lblMessage.setForeground(Color.ORANGE);
				lblInformation.setText("Have another go!");
				
				break;
				
			case VILLAINS_LAIR_CLEAR_MESSAGE:
				
				lblMessage.setText("");
				lblInformation.setText("");
				break;
			
			default:
				window.gameEventPerformed(event);
		
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		JOptionPane.showMessageDialog((Component) window, String.format("%s says \"%s\".", villainsLair.getVillain().getName(), villainsLair.getVillain().getTaunt()), "Villain's Taunt", JOptionPane.WARNING_MESSAGE);
		showDialogTimer.stop();
		
	}
}
