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
import game.minigame.GuessTheNumber;
import game.minigame.Minigame;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelGuessTheNumberGame extends GenericAreaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562504025376734580L;
	private int diceRollingLoop = 10;
	private JLabel lblVillainRoll;
	private JLabel lblHeroRoll;
	private GuessTheNumber guessTheNumber;
	//private BattleScreen battleScreen;
	JLabel lblWinner;
	JButton button;
	
	/**
	 * Create the panel.
	 */
	public PanelGuessTheNumberGame(Triggerable window, BattleScreen battleScreen) {
		
		guessTheNumber = (GuessTheNumber)battleScreen.getMinigame();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JLabel lblHeroDice;
		JLabel lblVillainDice;
		String lblVillainDiceValue = "0";
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblGuessTheNumberGame = new JLabel("Guess The Number Game");
		lblGuessTheNumberGame.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblGuessTheNumberGame);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Game starts when die is clicked to guess.");
		panel_2.add(lblClickStartToRoll);
		panel_6.add(panel_1);
		
		JButton btnOne = new JButton("One");
		panel_1.add(btnOne);
		
		JButton btnTwo = new JButton("Two");
		panel_1.add(btnTwo);
		
		JButton btnThree = new JButton("Three");
		panel_1.add(btnThree);
		
		JButton btnFour = new JButton("Four");
		panel_1.add(btnFour);
		
		JButton btnFive = new JButton("Five");
		panel_1.add(btnFive);
		
		JButton btnSix = new JButton("Six");
		panel_1.add(btnSix);
		
		//TODO
		/*\
		 Event handler to to detect button press
		 set the heroGuess to the number clicked
		 update the HeroGuess label value
		 for loop with timer to get and display dice values 
		 then update the VillainRoll to the final dice value
		 set the winner
		 set the ok button viable 
		 */
		
		//do we need a turns left label?
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblHeroRoll = new JLabel("Hero Guess          ");
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
		panel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
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
