package game.ui.gui.panels.games;

import javax.swing.JPanel;
import javax.swing.Timer;

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
	private JLabel lblVillainRoll;
	private JLabel lblHeroRoll;
	private boolean rollDice = false;

	/**
	 * Create the panel.
	 */
	public PanelDiceRollsGame(Triggerable window) {
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
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
				
				//get the value of the ok button.
				
				//If the Roll button is clicked populate the Hero and Villain Dice value labels with some 
				// random dice values that change say every .5 seconds for say 5 seconds
				// once the timer(s) run through fix the Hero and Dice Values
				// set Hero or Villain won
				// Enable the OK (to continue button)
				//TODO
				
				if (dlg.getDialogResult() == DialogResult.OK) {
						
					update();
					
				}
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Click Roll button to roll the dice");
		panel_2.add(lblClickStartToRoll);
		panel_1.add(btnStart);
		panel_6.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblHeroRoll = new JLabel("Hero Roll           ");
		lblHeroRoll.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblHeroRoll);
		
		lblVillainRoll = new JLabel("      Villain Roll");
		lblVillainRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblVillainRoll);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblHeroDice = new JLabel("lblHeroDiceValue");
		lblHeroDice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblHeroDice.setHorizontalAlignment(SwingConstants.LEFT);
		panel_4.add(lblHeroDice);
		
		JLabel lblVillainDice = new JLabel("lblVillainDiceValue");
		lblVillainDice.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblVillainDice.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblVillainDice);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JLabel lblWinner = new JLabel("lblWinnerText");
		panel_5.add(lblWinner);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JButton button = new JButton("OK");
		button.setEnabled(false);
		panel_7.add(button);
		button.setActionCommand("OK");
	}
	
	public void update() {
		
		repaint();
	}
		
	//Is this needed?
	/**
	 * Sets the winner text label to show that the Hero Won
	 */
	public void setHeroWinner() {
		//TODO
	}
	
	//Is this needed?
	/**
	 * Sets the winner text label to show that the Villain Won
	 */
	public void setVillainWinner() {
		//TODO
	}
		
}
