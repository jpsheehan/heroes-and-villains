package game.ui.gui.windows;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameEnvironment;
import game.Team;
import game.TeamFullException;
import game.character.Hero;
import game.character.HeroDeadException;
import game.character.HeroType;
import game.city.CityController;

import java.awt.Dimension;

import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.LoadingResourcesDialog;
import game.item.HealingItem;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.CardLayout;
import game.ui.gui.panels.MainGamePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class GameWindow implements GameEventListener {

	private JFrame frmHeroesAndVillains;
	private GameEnvironment gameEnvironment;
	private MainGamePanel mainGamePanel;
	private JPanel cardPanel;

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
		
		GameEventListener self = this;
		
		Dimension size = new Dimension(800, 600);
		frmHeroesAndVillains = new JFrame();
		frmHeroesAndVillains.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				// Load the resources
				(new LoadingResourcesDialog()).setVisible(true);
				
				mainGamePanel = new MainGamePanel(gameEnvironment, self);
				cardPanel.add(mainGamePanel);
				CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
				cardLayout.last(cardPanel);
				
			}
		});
		frmHeroesAndVillains.setTitle("Heroes and Villains - Campus Edition");
		
		frmHeroesAndVillains.setBounds(100, 100, 450, 300);
		frmHeroesAndVillains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHeroesAndVillains.setMaximumSize(size);
		frmHeroesAndVillains.setSize(size);
		
		createTestEnvironment();
		frmHeroesAndVillains.getContentPane().setLayout(new BorderLayout(0, 0));
		
		cardPanel = new JPanel();
		frmHeroesAndVillains.getContentPane().add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
	}
	
	public void show() {
		this.frmHeroesAndVillains.setVisible(true);
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
		team.getInventory().add(HealingItem.fromStrings("Curry"));
		
		gameEnvironment.setTeam(team);
		gameEnvironment.setCityController(new CityController(3));
		
	}

	@Override
	public void gameEventPerformed(GameEvent event) {
		
		switch (event) {
		
			case GAME_WON:
				
				JOptionPane.showMessageDialog(null, "Game won!");
				frmHeroesAndVillains.dispose();
				
				break;
				
			case GAME_LOST:
				
				JOptionPane.showMessageDialog(null, "Game lost!");
				frmHeroesAndVillains.dispose();
				
				break;
			
			default:
				throw new AssertionError();
		
		}
		
	}
}
