package game.ui.gui.panels.games;

import javax.swing.JPanel;

import game.BattleScreen;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

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

/**
 * GUI to Dice Rolls (mini)Game 
 * @version 1.0
 * @author manuh
 * 
 * @version 2.0
 * @author jesse
 * Added animation, 
 *
 */
public class DiceRollsGamePanel extends JPanel implements GameEventListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -562504025376734580L;
	private JLabel lblVillainRoll;
	private JLabel lblHeroRoll;
	private DiceRolls diceRolls;
	private GameEventListener villainsLairPanel;
	private int completedAnimations;
	JLabel lblWinner;
	JButton btnStart; 
	
	/**
	 * Create the panel.
	 * @param villainsLairPanel The parent panel
	 * @param battleScreen The current BattleScreen
	 */
	public DiceRollsGamePanel(GameEventListener villainsLairPanel, BattleScreen battleScreen) {
		
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
		
		btnStart = new JButton("Roll!");
		btnStart.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.VILLAINS_LAIR_CLEAR_MESSAGE));
				
				btnStart.setEnabled(false);
				
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
	}
	
	public void update() {
		
		repaint();
	}
	
	private void disableAll() {
		
		btnStart.setEnabled(false);
		
	}

	@Override
	public void gameEventPerformed(GameEvent event) {
		
		if (event.getType() == GameEventType.ANIMATION_COMPLETE) {
			
			// Handle the event
			if (++completedAnimations == 2) {
				
				btnStart.setEnabled(true);
				
				// Check the status of the game here and update components/trigger events in the villainsLairPanel
				switch (diceRolls.getState()) {
					
					case WON:
						disableAll();
						villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_WON));
						break;
						
					case LOST:
						disableAll();
						villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_LOST));
						break;
					
					case DRAWN:
						villainsLairPanel.gameEventPerformed(new GameEvent(GameEventType.MINIGAME_DRAWN));
						break;
						
					case PLAYING:
						System.out.println(diceRolls.getState());
						break;
						
					default:
						throw new AssertionError();
				
				}
			}
			
		} else {
			
			// The parent must handle the event
			villainsLairPanel.gameEventPerformed(event);
			
		}
		
	}
}
