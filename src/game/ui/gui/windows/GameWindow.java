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

import java.awt.CardLayout;

import game.ui.gui.panels.GameSetUpPanel;
import game.ui.gui.panels.MainGamePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import game.ui.gui.panels.MainMenuPanel;

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
		
		// Load the resources
		LoadingResourcesDialog dlg = new LoadingResourcesDialog();
		dlg.setLocationRelativeTo(frmHeroesAndVillains);
		dlg.setVisible(true);
		
		Dimension size = new Dimension(800, 600);
		frmHeroesAndVillains = new JFrame();
		frmHeroesAndVillains.setTitle("Heroes and Villains - Campus Edition");
		
		frmHeroesAndVillains.setBounds(100, 100, 450, 300);
		frmHeroesAndVillains.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHeroesAndVillains.setMaximumSize(size);
		frmHeroesAndVillains.setSize(size);
		
		frmHeroesAndVillains.getContentPane().setLayout(new BorderLayout(0, 0));
		
		cardPanel = new JPanel();
		frmHeroesAndVillains.getContentPane().add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		
		MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
		cardPanel.add(mainMenuPanel, "name_85970895571794");
		
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
		
		switch (event.getType()) {
		
			case GAME_WON:
				
				JOptionPane.showMessageDialog(null, "Game won!");
				frmHeroesAndVillains.dispose();
				
				break;
				
			case GAME_LOST:
				
				JOptionPane.showMessageDialog(null, "Game lost!");
				frmHeroesAndVillains.dispose();
				
				break;
				
			case NEW_GAME:

				GameSetUpPanel panel = new GameSetUpPanel(this);
				cardPanel.add(panel);
				CardLayout cardLayout = (CardLayout)cardPanel.getLayout();
				cardLayout.last(cardPanel);
				break;
				
			case START_GAME:
				GameEnvironment env = (GameEnvironment)event.getParameters();
				
				mainGamePanel = new MainGamePanel(env, this);
				cardPanel.add(mainGamePanel);
				CardLayout cardLayout2 = (CardLayout)cardPanel.getLayout();
				cardLayout2.last(cardPanel);
				
				break;
				
			case LOAD_GAME:
				JOptionPane.showMessageDialog(null, "Not yet implemented.");
				break;
				
			case QUIT_GAME:
				
				// TODO: Fix for production
				// int result = JOptionPane.showConfirmDialog(frmHeroesAndVillains, "Are you sure you want to quit?");
				int result = JOptionPane.YES_OPTION;
				
				if (result == JOptionPane.YES_OPTION) {
					
					frmHeroesAndVillains.dispose();
					
				}
				break;
			
			default:
				throw new AssertionError();
		
		}
		
	}
}
