package game.ui.gui.windows;


import javax.swing.JFrame;

import game.ui.gui.panels.MapPanel;

import java.awt.BorderLayout;
import game.ui.gui.panels.TeamSummaryPanel;
import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroDeadException;
import game.character.HeroType;
import game.city.AreaType;
import game.city.CityController;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
//import java.awt.Dimension;

import javax.swing.Box;
import game.ui.gui.panels.AreaSummaryPanel;
import game.ui.gui.panels.ShopAreaPanel;
import game.city.Shop;

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
//		Dimension size = new Dimension(800, 600);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
//		frame.setMaximumSize(size);
//		frame.setSize(size);
		
		createTestEnvironment();
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		TeamSummaryPanel teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		northPanel.add(horizontalGlue);
		
		MapPanel mapPanel = new MapPanel(getGameEnvironment().getCityController());
		northPanel.add(mapPanel);
		mapPanel.setLayout(null);
		
		AreaSummaryPanel areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		frame.getContentPane().add(areaSummaryPanel, BorderLayout.SOUTH);
		
		ShopAreaPanel shopAreaPanel = new ShopAreaPanel((Shop)this.getGameEnvironment().getCityController().getCurrentCity().getArea(AreaType.SHOP), this.getGameEnvironment().getTeam());
		frame.getContentPane().add(shopAreaPanel, BorderLayout.CENTER);
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	private GameEnvironment getGameEnvironment() {
		return this.gameEnvironment;
	}
	
	/**
	 * REMOVE FROM PRODUCTION!
	 * Creates a basic team and city controller for testing
	 */
	private void createTestEnvironment() {
		
		Team team = new Team("Team Name");
		try {
			team.addHero(new Hero("Steve", HeroType.ARTS_STUDENT));
			team.addHero(new Hero("Bob", HeroType.ENGINEERING_STUDENT));
			team.addHero(new Hero("Amy", HeroType.LAW_STUDENT));
			
			team.getHeroes().get(0).takeDamage(30);
			team.getHeroes().get(1).takeDamage(60);
			team.getHeroes().get(2).takeDamage(200);
		} catch (TeamFullException | HeroDeadException e) {
		}
		
		gameEnvironment.setTeam(team);
		gameEnvironment.setCityController(new CityController(3));
		
	}

}
