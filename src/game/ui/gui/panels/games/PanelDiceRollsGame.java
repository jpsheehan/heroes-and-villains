package game.ui.gui.panels.games;

import javax.swing.JPanel;

import game.BattleScreen;
import game.ui.gui.GameEvent;
import game.ui.gui.Triggerable;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.minigame.DiceRolls;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import game.ui.gui.components.DiePanel;
import java.awt.Component;
import javax.swing.Box;

public class PanelDiceRollsGame extends JPanel implements Triggerable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562504025376734580L;
	private JLabel lblVillainRoll;
	private JLabel lblHeroRoll;
	private DiceRolls diceRolls;
	private Triggerable villainsLairPanel;
	private int completedAnimations;
	JLabel lblWinner;
	JButton button;

	/**
	 * Create the panel.
	 */
	public PanelDiceRollsGame(Triggerable villainsLairPanel, BattleScreen battleScreen) {
		
		diceRolls = (DiceRolls)battleScreen.getMinigame();
		
		this.villainsLairPanel = villainsLairPanel;
		
		DiePanel herosDie = new DiePanel(this),
				villainsDie = new DiePanel(this);
		
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

				diceRolls.doTurn(null);
				completedAnimations = 0;
				herosDie.roll(diceRolls.getHeroLastTurn());
				villainsDie.roll(diceRolls.getVillainLastTurn());

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
		
		lblHeroRoll = new JLabel("Hero's Roll");
		lblHeroRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblHeroRoll);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_3.add(horizontalStrut_1);
		
		lblVillainRoll = new JLabel("Villain's Roll");
		lblVillainRoll.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblVillainRoll);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		panel_4.add(herosDie);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut);
		
		panel_4.add(villainsDie);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		JLabel lblWinner = new JLabel("lblWinnerText");
		lblWinner.setEnabled(false);
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
		lblWinner.setEnabled(true);
	}

	@Override
	public void trigger(GameEvent event) {
		
		if (event == GameEvent.ANIMATION_COMPLETE) {
			
			// Handle the event
			if (++completedAnimations == 2) {
				
				// The next three lines are just for debugging purposes
				System.out.println("Both animations complete!");
				System.out.println(String.format("Hero: %d", diceRolls.getHeroLastTurn()));
				System.out.println(String.format("Vill: %d", diceRolls.getVillainLastTurn()));
				
				// TODO: Check the status of the game here and update components/trigger events in the villainsLairPanel
			}
			
		} else {
			
			// The parent must handle the event
			villainsLairPanel.trigger(event);
			
		}
		
	}
}
