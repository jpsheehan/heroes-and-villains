package game.ui.gui.panels;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;

import game.GameEnvironment;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 4137926196390658257L;

	/**
	 * Create the panel.
	 * @param parent
	 */
	public MainMenuPanel(GameEventListener parent) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblTitle = new JLabel("Heroes and Villains");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 32));
		panel_1.add(lblTitle);
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		
		JLabel lblSubtitle = new JLabel("Campus Edition");
		lblSubtitle.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_6.add(lblSubtitle);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		
		JLabel lblNewLabel = new JLabel("Written by Manu Hamblyn and Jesse Sheehan");
		panel_5.add(lblNewLabel);
		
		JPanel panel_7 = new JPanel();
		add(panel_7);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_7.add(verticalStrut);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.NEW_GAME));
				
			}
		});
		panel_2.add(btnNewGame);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JButton btnLoadGame = new JButton("Continue");
		btnLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.LOAD_GAME));
				
			}
		});
		btnLoadGame.setEnabled(GameEnvironment.doesSaveStateExist());
		panel_3.add(btnLoadGame);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		JButton btnQuitGame = new JButton("Quit Game");
		btnQuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.QUIT_GAME));
				
			}
		});
		panel_4.add(btnQuitGame);
		
	}
}
