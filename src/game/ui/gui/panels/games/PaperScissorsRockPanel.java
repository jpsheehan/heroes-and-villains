package game.ui.gui.panels.games;

import javax.swing.JPanel;
import game.BattleScreen;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEventListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.minigame.PaperScissorsRock;
import game.minigame.PaperScissorsRockMove;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 * GUI to Paper Scissors Rock (mini)Game 
 * @version 1.0
 * @author manuh
 * 
 * @version 2.0
 * @author jesse
 * Improved the flow / layout
 */
public class PaperScissorsRockPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6569531617355279249L;
	private PaperScissorsRock paperScissorsRock;
	JLabel lblWinner;
	JButton btnReady;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	PaperScissorsRockMove move = PaperScissorsRockMove.SCISSORS;
	JRadioButton rdbtnRock, rdbtnPaper, rdbtnScissors;
	
	/**
	 * Create the panel.
	 */
	public PaperScissorsRockPanel(GameEventListener villainsLairPanel, BattleScreen battleScreen) {
		
		paperScissorsRock = (PaperScissorsRock)battleScreen.getMinigame();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblGuessTheNumberGame = new JLabel("Paper, Scissors, Rock! Game");
		lblGuessTheNumberGame.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		panel.add(lblGuessTheNumberGame);
		
		JPanel panel_1 = new JPanel();
		panel_6.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Click \"Start\", then click \"Paper\", \"Scissors\" or \"Rock\"");
		panel_2.add(lblClickStartToRoll);
		
		btnReady = new JButton("Ready!");
		btnReady.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_1.add(btnReady);
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		rdbtnPaper = new JRadioButton("Paper");
		buttonGroup_1.add(rdbtnPaper);
		rdbtnPaper.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnPaper);
		rdbtnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move = PaperScissorsRockMove.PAPER;
			}	
		});
		
		rdbtnScissors = new JRadioButton("Scissors");
		buttonGroup_1.add(rdbtnScissors);
		rdbtnScissors.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnScissors);
		rdbtnScissors.setSelected(true);
		rdbtnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move = PaperScissorsRockMove.SCISSORS;
			}	
		});
		
		rdbtnRock = new JRadioButton("Rock");
		buttonGroup_1.add(rdbtnRock);
		rdbtnRock.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel_3.add(rdbtnRock);
		rdbtnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				move = PaperScissorsRockMove.ROCK;
			}	
		});
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JLabel lblHeroChoice = new JLabel();
		lblHeroChoice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_4.add(lblHeroChoice);
		lblHeroChoice.setHorizontalAlignment(SwingConstants.LEFT);
		lblHeroChoice.setEnabled(false);
		
		JLabel lblVillainChoice = new JLabel();
		lblVillainChoice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		panel_4.add(lblVillainChoice);
		lblVillainChoice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVillainChoice.setEnabled(false);
		
		JLabel lblHeroChoose = new JLabel("Hero's Choice:");
		panel_5.add(lblHeroChoose);
		
		JLabel lblVillainChoose = new JLabel("Villain's Choice:");
		panel_5.add(lblVillainChoose);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
				
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.VILLAINS_LAIR_CLEAR_MESSAGE));
				
				if (move == null) {
					throw new AssertionError();
				}
				
				paperScissorsRock.doTurn(move);
				
				lblHeroChoice.setText(paperScissorsRock.getHeroLastTurn().toString());
				lblVillainChoice.setText(paperScissorsRock.getVillainLastTurn().toString());
				
				//disable the start button if run out of turns
				
				if (paperScissorsRock.getRemainingTurns() == 0) {

					disableAll();
					
					// Check the status of the game here and update components/trigger events in the villainsLairPanel
					switch (paperScissorsRock.getState()) {
						
						case WON:
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_WON));
							break;
							
						case LOST:
							villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_LOST));
							break;
							
						default:
							throw new AssertionError();
					
					}
					
				} else {
					
					villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_DRAWN));
						
				}

			}
		});	
	}
	
	private void disableAll() {
		
		rdbtnPaper.setEnabled(false);
		rdbtnRock.setEnabled(false);
		rdbtnScissors.setEnabled(false);
		btnReady.setEnabled(false);
		
	}
}
