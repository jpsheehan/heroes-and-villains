package game.ui.gui.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import game.ui.gui.panels.MapPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import game.ui.gui.panels.TeamSummaryPanel;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;

import javax.swing.JLabel;
import game.city.CityController;

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
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		Team team = new Team("Team Name");
		try {
			team.addHero(new Hero("Steve", HeroType.ARTS_STUDENT));
			team.addHero(new Hero("Bob", HeroType.ENGINEERING_STUDENT));
			team.addHero(new Hero("Amy", HeroType.LAW_STUDENT));
		} catch (TeamFullException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TeamSummaryPanel teamSummaryPanel = new TeamSummaryPanel(team);
		frame.getContentPane().add(teamSummaryPanel, BorderLayout.NORTH);
		
		MapPanel mapPanel = new MapPanel((CityController) null);
		frame.getContentPane().add(mapPanel, BorderLayout.EAST);
	}
}
