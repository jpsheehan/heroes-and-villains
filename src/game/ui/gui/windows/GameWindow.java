package game.ui.gui.windows;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameEnvironment;

import java.awt.Dimension;

import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.LoadingResourcesDialog;

import game.ui.gui.panels.GameSetUpPanel;
import game.ui.gui.panels.MainGamePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import game.ui.gui.panels.MainMenuPanel;

public class GameWindow implements GameEventListener {

	/**
	 * The frame in which all the components reside.
	 */
	private JFrame frmHeroesAndVillains;
	
	private JPanel shownPanel;
	private JPanel panelHolder;

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
		
		panelHolder = new JPanel();
		panelHolder.setLayout(new BorderLayout(0, 0));
		frmHeroesAndVillains.getContentPane().add(panelHolder, BorderLayout.CENTER);
		
		switchPanel(new MainMenuPanel(this));
		
	}
	
	/**
	 * Shows the window.
	 */
	public void show() {
		this.frmHeroesAndVillains.setVisible(true);
	}

	/**
	 * Triggers a GameEvent, valid GameEventTypes are:
	 * 	- GAME_WON: When the last villain has been defeated.
	 *  - GAME_LOST: When the last hero has died.
	 *  - START_GAME: When the Team and CityController have been created. Pass in a GameEnvironment instance as a parameter.
	 *  - NEW_GAME: When the "New Game" button has been clicked in the main menu.
	 *  - LOAD_GAME: When the "Load Game" button has been clicked in the main menu.
	 *  - QUIT_GAME: When the "Quit Game" button has been clicked in the main menu.
	 *  - MAIN_MENU: When the main menu should be shown.
	 * @param event The event to trigger.
	 */
	@Override
	public void gameEventPerformed(GameEvent event) {
		
		switch (event.getType()) {
		
			case GAME_WON:
				
				// TODO: Make more better.
				// Show a message saying that the game was won!
				JOptionPane.showMessageDialog(frmHeroesAndVillains, "Game won!");
				frmHeroesAndVillains.dispose();
				break;
				
			case GAME_LOST:
				
				// TODO: Make more better.
				// Show a message saying that the game was lost!
				JOptionPane.showMessageDialog(frmHeroesAndVillains, "Game lost!");
				frmHeroesAndVillains.dispose();
				break;
				
			case NEW_GAME:
				switchPanel(new GameSetUpPanel(this));
				break;
				
			case START_GAME:
				GameEnvironment env = (GameEnvironment)event.getParameters();
				switchPanel(new MainGamePanel(env, this));
				
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
				
			case MAIN_MENU:
				switchPanel(new MainMenuPanel(this));
				break;
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
	private void switchPanel(JPanel newPanel) {
		
		if (shownPanel != null) {
			panelHolder.remove(shownPanel);
		}

		shownPanel = newPanel;
		panelHolder.add(shownPanel, BorderLayout.CENTER);
		
		frmHeroesAndVillains.revalidate();
		frmHeroesAndVillains.repaint();
	}
}
