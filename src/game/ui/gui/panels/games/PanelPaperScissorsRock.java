package game.ui.gui.panels.games;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.BattleScreen;
import game.GeneralHelpers;
import game.Team;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEvent;
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
import game.minigame.GuessTheNumber;
import game.minigame.Minigame;
import game.minigame.PaperScissorsRock;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PanelPaperScissorsRock extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6569531617355279249L;
	private Triggerable villainsLairPanel;
	private PaperScissorsRock paperScissorsRock;
	JLabel lblWinner;
	JButton btnStartTimer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	
	/**
	 * Create the panel.
	 */
	public PanelPaperScissorsRock(Triggerable villainsLairPanel, BattleScreen battleScreen) {
		
		this.villainsLairPanel = villainsLairPanel;
		paperScissorsRock = (PaperScissorsRock)battleScreen.getMinigame();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblGuessTheNumberGame = new JLabel("Paper Scissors Rock Game");
		lblGuessTheNumberGame.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblGuessTheNumberGame);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Click \"Start\", then click \"Paper\", \"Scissors\" or \"Rock\"");
		panel_2.add(lblClickStartToRoll);
		
		panel_6.add(panel_1);
		
		btnStartTimer = new JButton("Start");
		btnStartTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_1.add(btnStartTimer);
		btnStartTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//when user clicks start
				//start timer say five seconds
				//while timer running
				//	show villain animation (continuously rolling icons of paper, scissors and rock
				//	get the radio button selection (use button group?)
				//	update the hero choice show icon of what they selected
				//when timer timer runs out lock the villain choice
				//do all the rest, won / lost / draw update etc
				
				villainsLairPanel.trigger(GameEvent.VILLAINS_LAIR_CLEAR_MESSAGE);
				
				switch (paperScissorsRock.getVillainLastTurn()) {
						case PAPER:
							lbl.setText("Paper  ");
							break;
						case SCISSORS:
							lbl.setText("Scissors");
							break;
						case ROCK:
							lbl.setText("Rock   ");
							break;
							
				}
				lblGameResult.setEnabled(true);
				
				//disable the guess button if run out of turns
				if (paperScissorsRock.getRemainingTurns() == 0) {

					// Check the status of the game here and update components/trigger events in the villainsLairPanel
					switch (paperScissorsRock.getState()) {
						
						case WON:
							disableAll();
							villainsLairPanel.trigger(GameEvent.MINIGAME_WON);
							break;
							
						case LOST:
							disableAll();
							villainsLairPanel.trigger(GameEvent.MINIGAME_LOST);
							break;
							
						case DRAWN:
							disableAll();
							villainsLairPanel.trigger(GameEvent.MINIGAME_DRAWN);
							break;
							
						default:
							throw new AssertionError();
					
					}
				
				}
			
			}
			
		});
		
		btnStartTimer.setActionCommand("OK");
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JRadioButton rdbtnPaper = new JRadioButton("Paper");
		buttonGroup_1.add(rdbtnPaper);
		rdbtnPaper.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnPaper);
		
		JRadioButton rdbtnScissors = new JRadioButton("Scissors");
		buttonGroup_1.add(rdbtnScissors);
		rdbtnScissors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnScissors);
		
		JRadioButton rdbtnRock = new JRadioButton("Rock");
		buttonGroup_1.add(rdbtnRock);
		rdbtnRock.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnRock);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblVillainChoose = new JLabel("Villain choose:");
		panel_4.add(lblVillainChoose);
		
		JLabel lblVillainChoice = new JLabel("Villain choice");
		panel_4.add(lblVillainChoice);
		lblVillainChoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVillainChoice.setEnabled(false);
		
		JLabel lblHeroChoose = new JLabel("Hero choose:");
		panel_5.add(lblHeroChoose);
		
		JLabel lblHeroChoice = new JLabel("Hero Choice");
		panel_5.add(lblHeroChoice);
		lblHeroChoice.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeroChoice.setEnabled(false);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblGameResult = new JLabel("Hero won / Villain won / Draw");
		panel_7.add(lblGameResult);
		lblGameResult.setEnabled(false);
		
	}
	
	private void disableAll() {
		
		btnStartTimer.setEnabled(false);
		
	}
	
}
