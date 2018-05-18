package game.ui.gui.panels.games;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.BattleScreen;
import game.GeneralHelpers;
import game.Team;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEventListener;
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
import game.minigame.PaperScissorsRockMove;

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
	private GameEventListener villainsLairPanel;
	private PaperScissorsRock paperScissorsRock;
	JLabel lblWinner;
	JButton btnStartTimer;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	int counterLoops = 5;
	int paperScissorsRockCode = 0;
	
	/**
	 * Create the panel.
	 */
	public PanelPaperScissorsRock(GameEventListener villainsLairPanel, BattleScreen battleScreen) {
		
		this.villainsLairPanel = villainsLairPanel;
		paperScissorsRock = (PaperScissorsRock)battleScreen.getMinigame();
		ActionListener buttonPress = null;
		
		
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
		panel_6.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Click \"Start\", then click \"Paper\", \"Scissors\" or \"Rock\"");
		panel_2.add(lblClickStartToRoll);
		
		btnStartTimer = new JButton("Start");
		btnStartTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_1.add(btnStartTimer);
		btnStartTimer.setActionCommand("OK");
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		paperScissorsRockCode = 2; // scissors
		
		JRadioButton rdbtnPaper = new JRadioButton("Paper");
		buttonGroup_1.add(rdbtnPaper);
		rdbtnPaper.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnPaper);
		rdbtnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paperScissorsRockCode = 1;
			}	
		});
		
		JRadioButton rdbtnScissors = new JRadioButton("Scissors");
		buttonGroup_1.add(rdbtnScissors);
		rdbtnScissors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnScissors);
		rdbtnScissors.setSelected(true);
		rdbtnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paperScissorsRockCode = 2;
			}	
		});
		
		JRadioButton rdbtnRock = new JRadioButton("Rock");
		buttonGroup_1.add(rdbtnRock);
		rdbtnRock.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnRock);
		rdbtnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paperScissorsRockCode = 3;
			}	
		});
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblHeroChoice = new JLabel("Hero Choice");
		lblHeroChoice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_4.add(lblHeroChoice);
		lblHeroChoice.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeroChoice.setEnabled(false);
		
		JLabel lblVillainChoice = new JLabel("Villain choice");
		lblVillainChoice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_4.add(lblVillainChoice);
		lblVillainChoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVillainChoice.setEnabled(false);
		
		JLabel lblHeroChoose = new JLabel("Hero choose:");
		panel_5.add(lblHeroChoose);
		
		JLabel lblVillainChoose = new JLabel("Villain choose:");
		panel_5.add(lblVillainChoose);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblGameResult = new JLabel("Hero won / Villain won / Draw");
		panel_7.add(lblGameResult);
		lblGameResult.setEnabled(false);
		
		btnStartTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				btnStartTimer.setEnabled(false);
				villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.VILLAINS_LAIR_CLEAR_MESSAGE));
				
				for (int i = 0 ; i < counterLoops ; i++) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						//do nothing?
						
					}
				switch (paperScissorsRockCode) {
					case 1:
						paperScissorsRock.doTurn(PaperScissorsRockMove.PAPER);
						break;
					case 2:
						paperScissorsRock.doTurn(PaperScissorsRockMove.SCISSORS);
						break;
					case 3:
						paperScissorsRock.doTurn(PaperScissorsRockMove.ROCK);
						break;
					default:
						//do nothing - possibly generate exception
						throw new AssertionError();
				}
				lblHeroChoice.setText(paperScissorsRock.getHeroLastTurn().toString());				
				lblHeroChoice.setEnabled(true);
				lblVillainChoice.setText(paperScissorsRock.getVillainLastTurn().toString());
				lblVillainChoice.setEnabled(true);
				
				//when user clicks start
				//start timer say five seconds
				//while timer running
				//	show villain animation (continuously rolling icons of paper, scissors and rock
				//	get the radio button selection (use button group?)
				//	update the hero choice by showing icon of what they chose
				//when timer runs out show icon of what the villain chose
				//do all the rest, won / lost / draw update etc
				
				lblGameResult.setEnabled(true);
				
				//disable the start button if run out of turns
				
				if (paperScissorsRock.getRemainingTurns() == 0) {

					// Check the status of the game here and update components/trigger events in the villainsLairPanel
					switch (paperScissorsRock.getState()) {
						
						case WON:
							disableAll();
							lblGameResult.setText("Hero won!");
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_WON));
							break;
							
						case LOST:
							disableAll();
							lblGameResult.setText("Villain won!");
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_LOST));
							break;
							
						case DRAWN:
							disableAll();
							lblGameResult.setText("Game drawn!");
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_DRAWN));
							break;
							
						default:
							throw new AssertionError();
					
						}
					}
				if (paperScissorsRock.getRemainingTurns() == 1 ) {
					//play again
					}
				}
			}
		});	
	}
	
	private void disableAll() {
		
		btnStartTimer.setEnabled(false);
		buttonGroup_1.clearSelection();
		
	}
}
