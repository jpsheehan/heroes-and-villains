package game.ui.gui.windows;


import javax.swing.JFrame;

import game.ui.gui.panels.MapPanel;

import java.awt.BorderLayout;
import game.ui.gui.panels.TeamSummaryPanel;
import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroType;

import game.city.CityController;

public class GameWindow {

	private JFrame frame;
	private GameEnvironment gameEnvironment;

	/**
	 * Create the application.
	 */
	public GameWindow(GameEnvironment env) {
		this.gameEnvironment = env;
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
			return;
		}
		
		gameEnvironment.setTeam(team);
		gameEnvironment.setCityController(new CityController(3));
		
		TeamSummaryPanel teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		frame.getContentPane().add(teamSummaryPanel, BorderLayout.NORTH);
		
		MapPanel mapPanel = new MapPanel(getGameEnvironment().getCityController());
		frame.getContentPane().add(mapPanel, BorderLayout.EAST);
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	private GameEnvironment getGameEnvironment() {
		return this.gameEnvironment;
	}

}
