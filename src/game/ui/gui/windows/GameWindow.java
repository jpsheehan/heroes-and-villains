package game.ui.gui.windows;


import javax.swing.JFrame;

import game.ui.gui.panels.MapPanel;
import game.ui.gui.panels.PowerUpDenPanel;

import java.awt.BorderLayout;
import game.ui.gui.panels.TeamSummaryPanel;
import game.ui.gui.panels.VillainsLairPanel;
import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroDeadException;
import game.character.HeroType;
import game.city.AreaType;
import game.city.CityController;
import game.city.Direction;
import game.city.IllegalMoveException;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import game.ui.gui.panels.AreaSummaryPanel;
import game.ui.gui.panels.HomeBasePanel;
import game.ui.gui.panels.HospitalPanel;
import game.ui.gui.panels.ShopAreaPanel;
import game.city.Shop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GameWindow {

	private JFrame frame;
	private GameEnvironment gameEnvironment;
	private JPanel currentAreaPanel;
	private AreaSummaryPanel areaSummaryPanel;
	private MapPanel mapPanel;
	private JPanel navigationPanel;
	private JButton btnSouth;
	private JButton btnWest;
	private JButton btnEast;
	private JPanel panel;

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
		Dimension size = new Dimension(800, 600);
		frame = new JFrame();
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setMaximumSize(size);
		frame.setSize(size);
		
		createTestEnvironment();
		
		JPanel northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		TeamSummaryPanel teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		northPanel.add(horizontalGlue);
		
		panel = new JPanel();
		northPanel.add(panel);
		
		navigationPanel = new JPanel();
		panel.add(navigationPanel);
		navigationPanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				
				// navigationPanel.setSize(128 + btnEast.getWidth(), navigationPanel.getHeight());
				
			}
		});
		navigationPanel.setLayout(new BorderLayout(0, 0));
		
		mapPanel = new MapPanel(getGameEnvironment().getCityController());
		navigationPanel.add(mapPanel, BorderLayout.CENTER);
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));
		
		JButton btnNorth = new JButton("N");
		btnNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getGameEnvironment().getCityController().move(Direction.NORTH);
					updateAreaPanel();
				} catch (IllegalMoveException e1) {}
			}
		});
		navigationPanel.add(btnNorth, BorderLayout.NORTH);
		
		btnSouth = new JButton("S");
		btnSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getGameEnvironment().getCityController().move(Direction.SOUTH);
					updateAreaPanel();
				} catch (IllegalMoveException e1) {}
			}
		});
		navigationPanel.add(btnSouth, BorderLayout.SOUTH);
		
		btnWest = new JButton("W");
		btnWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getGameEnvironment().getCityController().move(Direction.WEST);
					updateAreaPanel();
				} catch (IllegalMoveException e1) {}
			}
		});
		navigationPanel.add(btnWest, BorderLayout.WEST);
		
		btnEast = new JButton("E");
		btnEast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getGameEnvironment().getCityController().move(Direction.EAST);
					updateAreaPanel();
				} catch (IllegalMoveException e1) {}
			}
		});
		navigationPanel.add(btnEast, BorderLayout.EAST);
		
		areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		frame.getContentPane().add(areaSummaryPanel, BorderLayout.SOUTH);
		
		updateAreaPanel();
		
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
	
	private void updateAreaPanel() {
		
		if (currentAreaPanel != null) {
			
			frame.getContentPane().remove(currentAreaPanel);
			currentAreaPanel = null;
			
		}
		
		// Set the currentAreaPanel depending on the type of area the team is in
		switch (this.getGameEnvironment().getCityController().getCurrentArea().getType()) {
		
			case HOME_BASE:
				currentAreaPanel = new HomeBasePanel();
				break;
				
			case HOSPITAL:
				currentAreaPanel = new HospitalPanel();
				break;
				
			case POWER_UP_DEN:
				currentAreaPanel = new PowerUpDenPanel();
				break;
				
			case SHOP:
				currentAreaPanel = new ShopAreaPanel((Shop)this.getGameEnvironment().getCityController().getCurrentCity().getArea(AreaType.SHOP), this.getGameEnvironment().getTeam());
				break;
				
			case VILLAINS_LAIR:
				currentAreaPanel = new VillainsLairPanel();
				break;
		
		}
		
		if (currentAreaPanel != null) {
			
			frame.getContentPane().add(currentAreaPanel, BorderLayout.CENTER);
			areaSummaryPanel.update();
			
		}
		
		mapPanel.repaint();
		
	}
}
