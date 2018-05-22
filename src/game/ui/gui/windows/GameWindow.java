package game.ui.gui.windows;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import game.GameEnvironment;

import java.awt.Dimension;
import java.io.IOException;

import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.components.DebugMenuBar;
import game.ui.gui.dialogs.LoadingResourcesDialog;
import game.ui.gui.panels.GameLostPanel;
import game.ui.gui.panels.GameSetUpPanel;
import game.ui.gui.panels.GameWonPanel;
import game.ui.gui.panels.MainGamePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import game.ui.gui.panels.MainMenuPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow implements GameEventListener {

	/**
	 * The frame in which all the components reside.
	 */
	private JFrame frmHeroesAndVillains;
	
	private JPanel shownPanel;
	private JPanel panelHolder;
	private DebugMenuBar menuBar;
	
	private boolean debugMode;
	
	private GameEnvironment env;

	/**
	 * Create the application.
	 */
	public GameWindow(boolean debugMode) {
		this.debugMode = debugMode;
		
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
		frmHeroesAndVillains.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				
				int result = JOptionPane.showConfirmDialog(frmHeroesAndVillains, "Are you sure you want to quit?", "Quit Game", JOptionPane.YES_NO_OPTION);
				
				if (result == JOptionPane.YES_OPTION) {
					
					if (env != null) {
						
						try {
							
							env.saveState();
							
						} catch (IOException e) {
							
							result = JOptionPane.showConfirmDialog(frmHeroesAndVillains, "Could not save the game. Are you sure you want to exit?", "Error", JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION);
							
							if (result == JOptionPane.NO_OPTION) {
								
								return;
								
							}
							
						}
						
					}
					
					frmHeroesAndVillains.dispose();
					// Thread.currentThread().stop();
					
				}
			}
		});
		frmHeroesAndVillains.setTitle("Heroes and Villains - Campus Edition");
		
		frmHeroesAndVillains.setBounds(100, 100, 450, 300);
		frmHeroesAndVillains.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmHeroesAndVillains.setMaximumSize(size);
		frmHeroesAndVillains.setSize(size);
		
		frmHeroesAndVillains.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelHolder = new JPanel();
		panelHolder.setLayout(new BorderLayout(0, 0));
		frmHeroesAndVillains.getContentPane().add(panelHolder, BorderLayout.CENTER);
		
		switchPanel(new MainMenuPanel(this));
			
		menuBar = new DebugMenuBar(this);
		frmHeroesAndVillains.setJMenuBar(menuBar);
		
		if (!debugMode) {
			
			menuBar.setVisible(false);
			menuBar.setEnabled(false);
			
		}
		
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
				
				// Show a message saying that the game was won!
				switchPanel(new GameWonPanel(this));
				break;
				
			case GAME_LOST:
				
				// Show a message saying that the game was lost!
				switchPanel(new GameLostPanel(this));
				break;
				
			case NEW_GAME:
				switchPanel(new GameSetUpPanel(this));
				break;
				
			case START_GAME:
				env = (GameEnvironment)event.getParameters();
				MainGamePanel mainGamePanel = new MainGamePanel(env, this);
				switchPanel(mainGamePanel);
				menuBar.setMainGamePanel(mainGamePanel);
				break;
				
			case LOAD_GAME:
				try {
					
					env = GameEnvironment.loadState();
					switchPanel(new MainGamePanel(env, this));
					
				} catch (ClassNotFoundException | IOException e) {
					
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmHeroesAndVillains, "Could not load the game state.");
					
				}
				
				break;
				
			case QUIT_GAME:
				frmHeroesAndVillains.dispatchEvent(new WindowEvent(frmHeroesAndVillains, WindowEvent.WINDOW_CLOSING));
				break;
				
			case MAIN_MENU:
				switchPanel(new MainMenuPanel(this));
				menuBar.setMainGamePanel(null);
				env = null;
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
