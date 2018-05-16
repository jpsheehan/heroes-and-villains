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

public class PanelGuessTheNumberGame extends GenericAreaPanel implements Triggerable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -5763958068575027679L;
	private Triggerable villainsLairPanel;
	//private int completedAnimations;
	private GuessTheNumber guessTheNumber;
	JLabel lblWinner;
	JButton btnGuess;
	private final JSlider slider = new JSlider();
	
	/**
	 * Create the panel.
	 */
	public PanelGuessTheNumberGame(Triggerable villainsLairPanel, BattleScreen battleScreen) {
		
		this.villainsLairPanel = villainsLairPanel;
		guessTheNumber = (GuessTheNumber)battleScreen.getMinigame();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//String lblVillainDiceValue = "0";
		
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
		
		JLabel lblClickStartToRoll = new JLabel("Move slider, then click \"Guess\" to start game");
		panel_2.add(lblClickStartToRoll);
		
		panel_6.add(panel_1);
		slider.setValue(3);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMinimum(guessTheNumber.getMinNumber());
		slider.setMaximum(guessTheNumber.getMaxNumber());
		panel_1.add(slider);
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblTurnsLeftText = new JLabel("Turns left:");
		panel_4.add(lblTurnsLeftText);
		
		JLabel lblTurnsLeft = new JLabel("Turns Left");
		panel_4.add(lblTurnsLeft);
		lblTurnsLeft.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTurnsLeft.setEnabled(false);
		
		JLabel lblGuessText = new JLabel("Hero Guess:");
		panel_5.add(lblGuessText);
		
		JLabel lblHeroGuess = new JLabel("Hero Guess");
		panel_5.add(lblHeroGuess);
		lblHeroGuess.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeroGuess.setEnabled(false);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		JLabel lblGuessResult = new JLabel("Guess High / Low / Correct");
		panel_7.add(lblGuessResult);
		lblGuessResult.setEnabled(false);
		
		btnGuess = new JButton("Guess");
		btnGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHeroGuess.setText((String.format("%d", slider.getValue())));
				guessTheNumber.doTurn(slider.getValue());
				lblTurnsLeft.setText(new Integer(guessTheNumber.getRemainingTurns()).toString());
				lblTurnsLeft.setEnabled(true);
				lblHeroGuess.setEnabled(true);
				
				villainsLairPanel.trigger(GameEvent.VILLAINS_LAIR_CLEAR_MESSAGE);
				
				switch (guessTheNumber.getVillainLastTurn()) {
						case TOO_HIGH:
							lblGuessResult.setText("Hero guess too high!");
							break;
						case TOO_LOW:
							lblGuessResult.setText("Hero guess too low!");
							break;
						case CORRECT:
							lblGuessResult.setText("Hero guess correct!");
							break;
						default:
							//
				}
				lblGuessResult.setEnabled(true);
				
				//disable the guess button if run out of turns
				if (guessTheNumber.getRemainingTurns() == 0) {
					disableAll();
				}
			}
		});
		
		btnGuess.setActionCommand("OK");
		panel_3.add(btnGuess);
		
	}
	
	public void update() {
		
		repaint();
	}
		
	/**
	 * Sets the winner text label to show that the Hero Won
	 */
	public void setGameWinner() {
		
		switch (guessTheNumber.getState()) {
			
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
	
	private void disableAll() {
		
		btnGuess.setEnabled(false);
		
	}
	
	@Override
	public void trigger(GameEvent event) {
		
		if (event == GameEvent.ANIMATION_COMPLETE) {
			
				// Check the status of the game here and update components/trigger events in the villainsLairPanel
				switch (guessTheNumber.getState()) {
					
					case WON:
						disableAll();
						villainsLairPanel.trigger(GameEvent.MINIGAME_WON);
						break;
						
					case LOST:
						disableAll();
						villainsLairPanel.trigger(GameEvent.MINIGAME_LOST);
						break;
					
					case DRAWN:
						//should never be a draw
						villainsLairPanel.trigger(GameEvent.MINIGAME_DRAWN);
						break;
						
					case PLAYING:
						System.out.println(guessTheNumber.getState());
						break;
						
					default:
						throw new AssertionError();
				
			}
			
			// The parent must handle the event
			villainsLairPanel.trigger(event);
			
		}
		
	}
}
