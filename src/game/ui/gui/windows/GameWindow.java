package game.ui.gui.windows;


import javax.swing.JFrame;

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

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Dimension;

import game.ui.gui.Triggerable;
import game.ui.gui.components.ImagePanel;
import game.ui.gui.panels.AreaSummaryPanel;
import game.city.Shop;
import game.item.HealingItem;

import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class GameWindow implements Triggerable {

	private JFrame frmHeroesAndVillains;
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
	private ImagePanel imagePanel;

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
		frmHeroesAndVillains = new JFrame();
		frmHeroesAndVillains.setTitle("Heroes and Villains - Campus Edition");
		
		frmHeroesAndVillains.setBounds(100, 100, 450, 300);
		frmHeroesAndVillains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHeroesAndVillains.getContentPane().setLayout(new BorderLayout(0, 0));
		frmHeroesAndVillains.setMaximumSize(size);
		frmHeroesAndVillains.setSize(size);
		
		createTestEnvironment();
		
		JPanel northPanel = new JPanel();
		frmHeroesAndVillains.getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		imagePanel = new ImagePanel();
		northPanel.add(imagePanel);
		// imagePanel.setImagePath("test.jpg");
		
		navigationPanelHolder = new JPanel();
		northPanel.add(navigationPanelHolder);
		
		navigationPanel = new NavigationPanel(this, this.getGameEnvironment().getCityController());
		navigationPanelHolder.add(navigationPanel);
		
		areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		frmHeroesAndVillains.getContentPane().add(areaSummaryPanel, BorderLayout.SOUTH);
		
		areaPanelHolderHolder = new JPanel();
		areaPanelHolderHolder.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmHeroesAndVillains.getContentPane().add(areaPanelHolderHolder, BorderLayout.CENTER);
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
		this.frmHeroesAndVillains.setVisible(true);
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
		
		team.getInventory().add(HealingItem.fromStrings("Coffee"));
		
		gameEnvironment.setTeam(team);
		gameEnvironment.setCityController(new CityController(3));
		
	}
	
	@Override
	public void triggerUpdateNavigation() {
		
		// Prompt for confirmation before entering a Villain's Lair
		if (getGameEnvironment().getCityController().getCurrentArea().getType() == AreaType.VILLAINS_LAIR) {
			
			int res = JOptionPane.showConfirmDialog(null, "You are about to enter a Villain's Lair. Are you sure you want to enter?", null, JOptionPane.YES_NO_OPTION);
			
			if (res != JOptionPane.YES_OPTION) {
				
				getGameEnvironment().getCityController().goTo(Direction.CENTRE);
				
			}
			
		}
		
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
				currentAreaPanel = new PowerUpDenPanel(this, this.getGameEnvironment().getTeam());
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
