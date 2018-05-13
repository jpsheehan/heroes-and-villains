package game.ui.gui.windows;


import javax.swing.JFrame;

import game.ui.gui.panels.MapPanel;
import game.ui.gui.panels.NavigationPanel;

import java.awt.BorderLayout;
import game.ui.gui.panels.TeamSummaryPanel;
import game.ui.gui.panels.areas.GenericAreaPanel;
import game.ui.gui.panels.areas.HomeBasePanel;
import game.ui.gui.panels.areas.HospitalPanel;
import game.ui.gui.panels.areas.PowerUpDenPanel;
import game.ui.gui.panels.areas.ShopAreaPanel;
import game.ui.gui.panels.areas.VillainsLairPanel;
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

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;

import game.ui.gui.Triggerable;
import game.ui.gui.panels.AreaSummaryPanel;
import game.ui.gui.panels.ImagePanel;
import game.city.Shop;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class GameWindow implements Triggerable {

	private JFrame frame;
	private GameEnvironment gameEnvironment;
	private GenericAreaPanel currentAreaPanel;
	private AreaSummaryPanel areaSummaryPanel;
	private NavigationPanel navigationPanel;
	private JPanel navigationPanelHolder;
	private JPanel areaPanelHolder;
	private TeamSummaryPanel teamSummaryPanel;
	private JPanel areaPanelHolderHolder;
	private JPanel areaTitle;
	private JLabel lblAreaTitle;
	private JPanel areaSubtitle;
	private JLabel lblAreaSubtitle;

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
		
		teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		ImagePanel imagePanel = new ImagePanel();
		northPanel.add(imagePanel);
		
		navigationPanelHolder = new JPanel();
		northPanel.add(navigationPanelHolder);
		
		navigationPanel = new NavigationPanel(this, this.getGameEnvironment().getCityController());
		navigationPanelHolder.add(navigationPanel);
		
		areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		frame.getContentPane().add(areaSummaryPanel, BorderLayout.SOUTH);
		
		areaPanelHolderHolder = new JPanel();
		areaPanelHolderHolder.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(areaPanelHolderHolder, BorderLayout.CENTER);
		areaPanelHolderHolder.setLayout(new BoxLayout(areaPanelHolderHolder, BoxLayout.Y_AXIS));
		
		areaTitle = new JPanel();
		areaPanelHolderHolder.add(areaTitle);
		areaTitle.setLayout(new BoxLayout(areaTitle, BoxLayout.X_AXIS));
		
		lblAreaTitle = new JLabel("NULL");
		lblAreaTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		areaTitle.add(lblAreaTitle);
		
		areaSubtitle = new JPanel();
		areaPanelHolderHolder.add(areaSubtitle);
		areaSubtitle.setLayout(new BoxLayout(areaSubtitle, BoxLayout.X_AXIS));
		
		lblAreaSubtitle = new JLabel("NULL");
		lblAreaSubtitle.setFont(new Font("Dialog", Font.BOLD, 14));
		areaSubtitle.add(lblAreaSubtitle);
		
		areaPanelHolder = new JPanel();
		areaPanelHolderHolder.add(areaPanelHolder);
		
		triggerUpdateNavigation();
		
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
			
			team.getHeroes()[0].takeDamage(30);
			team.getHeroes()[1].takeDamage(60);
			team.getHeroes()[2].takeDamage(200);
		} catch (TeamFullException | HeroDeadException e) {
		}
		
		gameEnvironment.setTeam(team);
		gameEnvironment.setCityController(new CityController(3));
		
	}
	
	@Override
	public void triggerUpdateNavigation() {
		
		if (currentAreaPanel != null) {
			
			areaPanelHolder.remove(currentAreaPanel);
			currentAreaPanel = null;
			
		}
		
		// Set the currentAreaPanel depending on the type of area the team is in
		switch (this.getGameEnvironment().getCityController().getCurrentArea().getType()) {
		
			case HOME_BASE:
				currentAreaPanel = new HomeBasePanel(this, this.getGameEnvironment().getTeam().getInventory(), this.getGameEnvironment().getCityController());
				break;
				
			case HOSPITAL:
				currentAreaPanel = new HospitalPanel(this, this.getGameEnvironment().getTeam());
				break;
				
			case POWER_UP_DEN:
				currentAreaPanel = new PowerUpDenPanel(this);
				break;
				
			case SHOP:
				currentAreaPanel = new ShopAreaPanel(this, (Shop)this.getGameEnvironment().getCityController().getCurrentCity().getArea(AreaType.SHOP), this.getGameEnvironment().getTeam());
				break;
				
			case VILLAINS_LAIR:
				currentAreaPanel = new VillainsLairPanel(this);
				break;
		
		}
		
		if (currentAreaPanel != null) {
			
			areaPanelHolder.add(currentAreaPanel, BorderLayout.CENTER);
			areaSummaryPanel.update();
			lblAreaTitle.setText(String.format("%s > %s", getGameEnvironment().getCityController().getCurrentCity().getName(), getGameEnvironment().getCityController().getCurrentArea().getName()));
			lblAreaSubtitle.setText(getGameEnvironment().getCityController().getCurrentArea().getType().toString());
			currentAreaPanel.update();
			
		}
		
		navigationPanel.update();
		
		areaPanelHolderHolder.repaint();
		
	}

	@Override
	public void triggerUpdateTeam() {
		teamSummaryPanel.update();
	}
	
}
