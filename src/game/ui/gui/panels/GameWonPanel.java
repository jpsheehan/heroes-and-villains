package game.ui.gui.panels;

import javax.swing.JPanel;

import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;

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
	public GameWonPanel(GameEventListener parent) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblGameWon = new JLabel("GAME WON!");
		lblGameWon.setFont(new Font("Dialog", Font.BOLD, 32));
		panel.add(lblGameWon);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblYouGraduatedUniversity = new JLabel("You graduated university with a GPA of");
		panel_1.add(lblYouGraduatedUniversity);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JLabel lblGpa = new JLabel("GPA");
		lblGpa.setFont(new Font("Dialog", Font.BOLD, 24));
		panel_2.add(lblGpa);
		
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
