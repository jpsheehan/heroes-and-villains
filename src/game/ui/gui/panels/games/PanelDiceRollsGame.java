package game.ui.gui.panels.games;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.BattleScreen;
import game.Team;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.Triggerable;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.dialogs.ItemSelectionDialog;
import game.ui.gui.panels.areas.GenericAreaPanel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import game.item.HealingItem;
import game.item.ItemType;
import game.minigame.DiceRolls;
import game.minigame.Minigame;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelDiceRollsGame extends GenericAreaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562504025376734580L;
	private int diceRollingLoop = 10;
	private JLabel lblVillainRoll;
	private JLabel lblHeroRoll;
	private DiceRolls diceRolls;
	//private BattleScreen battleScreen;
	JLabel lblWinner;
	JButton button;
	
	private boolean rollDice = false;

	/**
	 * Create the panel.
	 */
	public PanelDiceRollsGame(Triggerable window, BattleScreen battleScreen) {
		
		diceRolls = (DiceRolls)battleScreen.getMinigame();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel lblHeroDice;
		JLabel lblVillainDice;
		String lblHeroDiceValue = "0";
		String lblVillainDiceValue = "0";
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblDiceRollsGame = new JLabel("Dice Rolls Game");
		lblDiceRollsGame.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblDiceRollsGame);
		
		JPanel panel_1 = new JPanel();
		
		JButton btnStart = new JButton("Roll (the dice) ..");
		btnStart.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//If the Roll button is clicked populate the Hero and Villain Dice value labels with some 
				// random dice values that change say every .5 seconds for say 10 loops
				// set Hero or Villain won
				// Enable the OK (to continue button)
				
				for (int i = 0; i < diceRollingLoop ; i++) {
					//get dice values and display on labels
					diceRolls.doTurn(null);
					lblHeroRoll.setText(diceRolls.getHeroLastTurn().toString());
					lblVillainRoll.setText(diceRolls.getHeroLastTurn().toString());
					
					//pause 0.5 seconds
					(new Timer(500, null)).start();
				}
				//Update the winner (may remove this, if implemented in BattleScreen Panel / Villains Lair Panel)
				setGameWinner();
				//Set the OK button visible (may remove this, if implemented in BattleScreen Panel / Villains Lair Panel)
				button.setEnabled(true);
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Click \"Roll\" to start");
		panel_2.add(lblClickStartToRoll);
		panel_1.add(btnStart);
		panel_6.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblHeroRoll = new JLabel("Hero Roll           ");
		lblHeroRoll.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblHeroRoll);
		
		JLabel lblVillainRoll = new JLabel("      Villain Roll");
		lblVillainRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblVillainRoll);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		lblHeroDice = new JLabel("lblHeroDiceValue");
		lblHeroDice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHeroDice.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblHeroDice);
		
		lblVillainDice = new JLabel("lblVillainDiceValue");
		lblVillainDice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblVillainDice.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblVillainDice);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JLabel lblWinner = new JLabel("lblWinnerText");
		panel_5.add(lblWinner);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		button = new JButton("OK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
				System.out.println("Exit this panel.");
			}
		});
		button.setEnabled(false);
		panel_7.add(button);
		button.setActionCommand("OK");
	}
	
	public void update() {
		
		repaint();
	}
		
	/**
	 * Sets the winner text label to show that the Hero Won
	 */
	public void setGameWinner() {
		
		switch (diceRolls.getState()) {
			
		case WON:
			lblWinner.setText("The Hero Won!");
			break;
		case LOST:	
			lblWinner.setText("The Villain Won!");
			break;
		case DRAWN:
			lblWinner.setText("The game was drawn");
			break;
		default:
			lblWinner.setText("Unknown");
		}
		
	}
	
			
}
