package game.ui.gui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import game.ui.gui.panels.MapPanel;
import game.city.CityController;
import game.city.Direction;
import game.city.IllegalMoveException;

import java.awt.BorderLayout;
import java.awt.Color;

public class GameWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CityController cc = new CityController(3);
		// cc.useMap(new Map("","", 10));
		try {
			cc.move(Direction.SOUTH);
			cc.move(Direction.NORTH);
			cc.move(Direction.NORTH);
		} catch (IllegalMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapPanel mapPanel =  new MapPanel(cc);
		mapPanel.setBackground(Color.BLACK);
		frame.getContentPane().add(mapPanel, BorderLayout.CENTER);
	}

}
