package game.ui.gui.panels;

import game.ui.gui.panels.NavigationPanel;

import java.awt.BorderLayout;
import java.awt.Component;

import game.ui.gui.panels.TeamSummaryPanel;
import game.ui.gui.panels.areas.GenericAreaPanel;
import game.ui.gui.panels.areas.HomeBasePanel;
import game.ui.gui.panels.areas.HospitalPanel;
import game.ui.gui.panels.areas.PowerUpDenPanel;
import game.ui.gui.panels.areas.ShopAreaPanel;
import game.ui.gui.panels.areas.VillainsLairPanel;
import game.ui.gui.windows.GameWindow;
import game.GameEnvironment;
import game.GameWonException;
import game.GeneralHelpers;
import game.character.Hero;
import game.city.Area;
import game.city.AreaType;
import game.city.CityController;
import game.city.Direction;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.GridLayout;

import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;
import game.ui.gui.components.ImagePanel;
import game.ui.gui.panels.AreaSummaryPanel;
import game.city.Shop;
import game.city.VillainsLair;
import game.randomevent.ItemGiftedException;
import game.randomevent.ItemRobbedException;
import game.randomevent.MoneyRobbedException;
import game.randomevent.RobberyPreventedException;

import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainGamePanel extends JPanel implements GameEventListener, ActionListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 6052659958229075403L;
	
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
	private GameEventListener window;
	private JPanel panel;
	private JButton btnSaveAndQuit;
	
	/**
	 * A timer for performing the random event and showing a dialog box when travelling to the next city.
	 */
	private Timer randomEventTimer;

	/**
	 * Create the application.
	 */
	public MainGamePanel(GameEnvironment env, GameEventListener window) {
		this.gameEnvironment = env;
		this.window = window;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Component self = this;

		setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		imagePanel = new ImagePanel();
		imagePanel.setImagePath("duck.jpg");
		northPanel.add(imagePanel);
		
		navigationPanelHolder = new JPanel();
		northPanel.add(navigationPanelHolder);
		
		navigationPanel = new NavigationPanel(this, this.getGameEnvironment().getCityController());
		navigationPanelHolder.add(navigationPanel);
		
		areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		add(areaSummaryPanel, BorderLayout.SOUTH);
		
		panel = new JPanel();
		areaSummaryPanel.add(panel);
		
		btnSaveAndQuit = new JButton("Save and Quit");
		btnSaveAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					gameEnvironment.saveState();
					window.gameEventPerformed(new GameEvent(GameEventType.MAIN_MENU));
					
				} catch (IOException e) {
					
					int res = JOptionPane.showConfirmDialog((Component) self, "Could not save the game. Are you sure you want to quit?", "Error", JOptionPane.ERROR_MESSAGE);
					
					if (res == JOptionPane.YES_OPTION) {
						
						window.gameEventPerformed(new GameEvent(GameEventType.MAIN_MENU));
						
					}
					
				}
			}
		});
		panel.add(btnSaveAndQuit);
		
		areaPanelHolderHolder = new JPanel();
		add(areaPanelHolderHolder);
		areaPanelHolderHolder.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
		areaPanelHolder.setLayout(new BorderLayout(0, 0));
				
		updateNavigation();
		
		randomEventTimer = new Timer(100, this);
		GameWindow.addTimer(randomEventTimer);
		
	}
		
	public GameEnvironment getGameEnvironment() {
		return this.gameEnvironment;
	}
	
	private void updateNavigation() {
		
		// Prompt for confirmation before entering a Villain's Lair
		if (getGameEnvironment().getCityController().getCurrentArea().getType() == AreaType.VILLAINS_LAIR && !gameEnvironment.getIgnoreRoomPrompt()) {
			
			int res = JOptionPane.showConfirmDialog((Component) this, "You are about to enter a Villain's Lair. You will not be able to leave until you beat the villain.\nAre you sure you want to enter?", "Caution", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if (res != JOptionPane.YES_OPTION) {
				
				getGameEnvironment().getCityController().goTo(Direction.CENTRE);
				
			}
			
		}
		
		if (currentAreaPanel != null) {
			
			areaPanelHolder.remove(currentAreaPanel);
			currentAreaPanel = null;
			
		}
		
		// Set the currentAreaPanel depending on the type of area the team is in
		
		Area area = this.getGameEnvironment().getCityController().getCurrentArea();
		
		switch (area.getType()) {
		
			case HOME_BASE:
				currentAreaPanel = new HomeBasePanel(this, this.getGameEnvironment().getTeam(), this.getGameEnvironment().getCityController());
				break;
				
			case HOSPITAL:
				currentAreaPanel = new HospitalPanel(this, this.getGameEnvironment().getTeam());
				break;
				
			case POWER_UP_DEN:
				currentAreaPanel = new PowerUpDenPanel(this, this.getGameEnvironment().getTeam());
				break;
				
			case SHOP:
				currentAreaPanel = new ShopAreaPanel(this, (Shop)area, this.getGameEnvironment().getTeam());
				break;
				
			case VILLAINS_LAIR:
				currentAreaPanel = new VillainsLairPanel(this, (VillainsLair)area, this.getGameEnvironment());
				break;
		
		}
		
		if (currentAreaPanel != null) {
			
			CityController cc = getGameEnvironment().getCityController();
			
			areaPanelHolder.add(currentAreaPanel, BorderLayout.CENTER);
			areaSummaryPanel.update();
			lblAreaTitle.setText(String.format("%s > %s", cc.getCurrentCity().getName(), cc.getCurrentArea().getName()));
			lblAreaSubtitle.setText(cc.getCurrentArea().getType().toString());
			currentAreaPanel.update();
			
			// Load the image of the area if it exists, otherwise load the duck instead.
			String imageName = String.format("%s.%s.jpg", cc.getCurrentCity().getType().getProperName(), cc.getCurrentArea().getType().toProperString());
			if (!GeneralHelpers.imageManager.contains(imageName)) {
				
				imageName = "duck.jpg";
				
			}
			
			imagePanel.setImagePath(imageName);
			
		}
		
		navigationPanel.update();
		
		areaPanelHolderHolder.repaint();
		
	}

	@Override
	public void gameEventPerformed(GameEvent event) {
		
		switch (event.getType()) {
		
			case TEAM_CHANGED:
				teamSummaryPanel.update();
				break;
				
			case NAVIGATION_CHANGED:
				updateNavigation();
				break;
				
			case GO_TO_NEXT_CITY:
				
				try {
					
					this.getGameEnvironment().getCityController().goToNextCity();
					updateNavigation();
					randomEventTimer.start();
					
				} catch (GameWonException e) {
					
					window.gameEventPerformed(new GameEvent(GameEventType.GAME_WON));
					
				}
				
				break;
				
			default:
				
				window.gameEventPerformed(event);
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// get the first non-dead hero
		Hero nonDeadHero = null;
		for (Hero hero : getGameEnvironment().getTeam().getHeroes()) {
			if (hero.isAlive()) {
				nonDeadHero = hero;
				break;
			}
		}
		
		try {
			
			// perform the random event and handle the outcome appropriately
			this.getGameEnvironment().getCityController().getCurrentCity().getRandomEvent().performEvent(getGameEnvironment().getTeam());
			
		} catch (RobberyPreventedException e) {
			
			JOptionPane.showMessageDialog((Component)this, String.format("A dodgy flatmate tried to take your %s! But %s used their knowledge of criminal law to deter them!", e.getItemNotRobbed().getName(), e.getLawStudent().getName()), "Random Event", JOptionPane.WARNING_MESSAGE);
			
		} catch (MoneyRobbedException e) {
			
			JOptionPane.showMessageDialog((Component)this, String.format("A dodgy flatmate went through %s's room and stole $%d. What a jerk!", nonDeadHero.getName(), e.getMoneyRobbed()), "Random Event", JOptionPane.WARNING_MESSAGE);
			gameEventPerformed(new GameEvent(GameEventType.TEAM_CHANGED));
			
		} catch (ItemRobbedException e) {
			
			JOptionPane.showMessageDialog((Component)this, String.format("A dodgy flatmate went through %s's stuff and stole a %s. That's not nice!", nonDeadHero.getName(), e.getItemRobbed().getName()), "Random Event", JOptionPane.WARNING_MESSAGE);
			
		} catch (ItemGiftedException e) {

			JOptionPane.showMessageDialog((Component)this,  String.format("The government announced that every student shall receive a free %s! Thanks Aunty Cindy!", e.getItemGifted().getName()), "Random Event", JOptionPane.INFORMATION_MESSAGE);
		}
		
		randomEventTimer.stop();
		
	}
	
}
