package game.ui.gui.panels;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.GameEventType;
import game.ui.gui.windows.GameWindow;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;

public class GameLostPanel extends JPanel implements ActionListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 1676245884611148409L;
	
	private JLabel lblGameLost;

	/**
	 * Create the panel.
	 * @param parent The parent who called this panel
	 */
	public GameLostPanel(GameEventListener parent) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblGameLost = new JLabel("GAME LOST!");
		lblGameLost.setFont(new Font("Dialog", Font.BOLD, 32));
		panel.add(lblGameLost);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblConsolation = new JLabel("Better luck next time.");
		panel_1.add(lblConsolation);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JButton btnReturnToMenu = new JButton("Return to Menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				parent.gameEventPerformed(new GameEvent(GameEventType.MAIN_MENU));
				
			}
		});
		panel_2.add(btnReturnToMenu);
		
		Timer timer = new Timer(200, this);
		timer.start();
		GameWindow.addTimer(timer);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (lblGameLost.getForeground() == Color.BLACK) {
			
			lblGameLost.setForeground(Color.RED);
			
		} else {
			
			lblGameLost.setForeground(Color.BLACK);
			
		}
		
		repaint();
	}

}
