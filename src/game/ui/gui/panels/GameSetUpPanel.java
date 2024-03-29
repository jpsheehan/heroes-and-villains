package game.ui.gui.panels;

import javax.swing.JPanel;

import game.GameEnvironment;
import game.Settings;
import game.Team;
import game.city.CityController;
import game.ui.gui.GameEventType;
import game.ui.gui.windows.GameWindow;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.JSlider;
import java.awt.FlowLayout;
import java.awt.BorderLayout;


public class GameSetUpPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6625880562455508713L;

	private GameEventListener parent;
	
	/**
	 * Create the panel.
	 * @param parent The parent who called this panel
	 */
	public GameSetUpPanel(GameEventListener parent) {

		this.parent = parent;
		
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
		sliderNumberOfCities.setMinimum(Settings.getCitiesMin());
		sliderNumberOfCities.setMaximum(Settings.getCitiesMax());
		sliderNumberOfCities.setMajorTickSpacing(1);
		sliderNumberOfCities.setSnapToTicks(true);
		sliderNumberOfCities.setPaintTicks(true);
		sliderNumberOfCities.setPaintLabels(true);
		panel_2.add(sliderNumberOfCities);
		panel_6.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		TeamCreationPanel teamCreationPanel = new TeamCreationPanel();
		panel_1.add(teamCreationPanel);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		//OK takes user to the main game panel. 
		// Could pop up a validation check window first: cancel would reEnable the New Game Menu and OK would continue to main game panel 
		JButton button_1 = new JButton("OK");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team team = teamCreationPanel.getTeam();
				
				if (team == null) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), teamCreationPanel.getTeamFailureReason(), "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
				
					GameEnvironment env = new GameEnvironment();
					env.setTeam(team);
					env.setCityController(new CityController(sliderNumberOfCities.getValue()));
					beginGame(env);
					
				}
			}
		});
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		button_1.setVerticalAlignment(SwingConstants.BOTTOM);
		button_1.setActionCommand("OK");
		panel_5.add(button_1);
		
		//cancel returns user back to Initial Startup Window 
		JButton button_2 = new JButton("Cancel");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.MAIN_MENU));
				
			}
		});
		button_2.setVerticalAlignment(SwingConstants.BOTTOM);
		button_2.setActionCommand("Cancel");
		panel_5.add(button_2);
	}
	
	
	private void beginGame(GameEnvironment env) {
		
		parent.gameEventPerformed(new GameEvent(GameEventType.START_GAME, env));
		
	}
	
}
