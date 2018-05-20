package game.ui.gui.panels;

import javax.swing.JPanel;

import game.BattleScreen;
import game.GameEnvironment;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.minigame.DiceRolls;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import game.ui.gui.components.DiePanel;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.dialogs.NewHeroDialog;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSlider;
import java.awt.FlowLayout;


public class GameSetUpPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6625880562455508713L;

	private GameEventListener parent;
	private GameEnvironment gameEnvironment;
	private int cities;
	
	/**
	 * Create the panel.
	 */
	public GameSetUpPanel(GameEventListener parent) {

		this.parent = parent;
		GameEnvironment gameEnvironment = new GameEnvironment();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblGameSetUpPanel = new JLabel("New Game Menu");
		lblGameSetUpPanel.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(lblGameSetUpPanel);
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblClickStartToRoll = new JLabel("Select number of cities to explore:");
		panel_2.add(lblClickStartToRoll);
		
		JSlider sliderNumberOfCities = new JSlider();
		// Min and Max currently hardcoded, better to get from Strings file
		sliderNumberOfCities.setMinimum(1);
		sliderNumberOfCities.setMaximum(3);
		sliderNumberOfCities.setMajorTickSpacing(1);
		sliderNumberOfCities.setSnapToTicks(true);
		sliderNumberOfCities.setPaintTicks(true);
		sliderNumberOfCities.setPaintLabels(true);
		panel_2.add(sliderNumberOfCities);
		panel_6.add(panel_1);
		
		JLabel lblCreateHero = new JLabel("Create (and name) a Hero :");
		panel_1.add(lblCreateHero);
		lblCreateHero.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnCreateHero = new JButton("Create Hero");
		btnCreateHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NewHeroDialog dlg = new NewHeroDialog();
				dlg.setVisible(true);
			}
		});
		panel_1.add(btnCreateHero);
		btnCreateHero.setFont(new Font("Dialog", Font.BOLD, 12));
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblCreateTeam = new JLabel("Create (and name) Team:");
		lblCreateTeam.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_3.add(lblCreateTeam);
		
		JButton btnCreateTeam = new JButton("Create Team");
		btnCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//when team created set the number of cities
				
				//(sliderNumberOfCities.getValue());
				
//				NewTeamDialog dlg = new NewTeamDialog();
//				dlg.setVisible(true);
			}
		});
		btnCreateTeam.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_3.add(btnCreateTeam);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		//OK takes user to the main game panel. 
		// Could pop up a validation check window first: cancel would reEnable the New Game Menu and OK would continue to main game panel 
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beginGame(gameEnvironment);
			}
		});
		button_1.setVerticalAlignment(SwingConstants.BOTTOM);
		button_1.setActionCommand("OK");
		panel_5.add(button_1);
		
		//cancel returns user back to Initial Startup Window 
		JButton button_2 = new JButton("Cancel");
		button_2.setVerticalAlignment(SwingConstants.BOTTOM);
		button_2.setActionCommand("Cancel");
		panel_5.add(button_2);
	}
	
	
	private void beginGame(GameEnvironment env) {
		
	parent.gameEventPerformed(new GameEvent(GameEventType.START_GAME, env));
		
	}
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			GameSetUpPanel panel = new GameSetUpPanel(parent);
			panel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			panel.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
}
