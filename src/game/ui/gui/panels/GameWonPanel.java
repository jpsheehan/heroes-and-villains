package game.ui.gui.panels;

import javax.swing.JPanel;

import game.GameEnvironment;
import game.GeneralHelpers;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;
import game.ui.text.TextUserInterfaceHelpers;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameWonPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -5091965106098637318L;

	/**
	 * Create the panel.
	 */
	public GameWonPanel(GameEventListener parent, GameEnvironment env) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblGameWon = new JLabel("GAME WON!");
		lblGameWon.setFont(new Font("Dialog", Font.BOLD, 32));
		panel.add(lblGameWon);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblYouGraduatedUniversity = new JLabel(String.format("%s graduated university in:", env.getTeam().getName()));
		panel_1.add(lblYouGraduatedUniversity);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		// format the time nicely
		int minutes = (int) (env.getNumberOfSeconds() / 60);
		int seconds = (int) (env.getNumberOfSeconds() % 60);
		
		JLabel lblTime = new JLabel(String.format("%s:%s",
				TextUserInterfaceHelpers.padLeft(new Integer(minutes).toString(), '0', 2),
				TextUserInterfaceHelpers.padLeft(new Integer(seconds).toString(), '0', 2)));
		
		lblTime.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_2.add(lblTime);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		JLabel lblTheTeamHad = new JLabel("The team had an average GPA of:");
		panel_4.add(lblTheTeamHad);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		
		float gpa = GeneralHelpers.getGPA(env);
		JLabel lblGpa = new JLabel(String.format("%.2f  %s", gpa, GeneralHelpers.getGradeLetter(gpa)));
		lblGpa.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_5.add(lblGpa);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JButton btnReturnToMain = new JButton("Return to Main Menu");
		btnReturnToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.gameEventPerformed(new GameEvent(GameEventType.MAIN_MENU));
			}
		});
		panel_3.add(btnReturnToMain);

	}

}
