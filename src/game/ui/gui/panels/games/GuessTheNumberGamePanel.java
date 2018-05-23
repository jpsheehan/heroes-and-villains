package game.ui.gui.panels.games;

import javax.swing.JPanel;

import game.BattleScreen;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.minigame.GuessTheNumber;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSlider;

public class GuessTheNumberGamePanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */

	private static final long serialVersionUID = -5763958068575027679L;
	private GuessTheNumber guessTheNumber;
	JLabel lblWinner;
	JButton btnGuess;
	private final JSlider slider = new JSlider();
	
	/**
	 * Create the panel.
	 */
	public GuessTheNumberGamePanel(GameEventListener villainsLairPanel, BattleScreen battleScreen) {
		
		guessTheNumber = (GuessTheNumber)battleScreen.getMinigame();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

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
				
				villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.VILLAINS_LAIR_CLEAR_MESSAGE));
				
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
							
				}
				lblGuessResult.setEnabled(true);
				
				//disable the guess button if run out of turns
				if (guessTheNumber.getRemainingTurns() == 0) {

					// Check the status of the game here and update components/trigger events in the villainsLairPanel
					switch (guessTheNumber.getState()) {
						
						case WON:
							disableAll();
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_WON));
							break;
							
						case LOST:
							disableAll();
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_LOST));
							break;
							
						default:
							throw new AssertionError();
					
					}
				
				}
			
			}
			
		});
		
		btnGuess.setActionCommand("OK");
		panel_3.add(btnGuess);
		
	}
	
	private void disableAll() {
		
		btnGuess.setEnabled(false);
		slider.setEnabled(false);
		
	}
	
}
