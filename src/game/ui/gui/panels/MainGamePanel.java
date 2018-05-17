package game.ui.gui.panels;

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
import game.GameWonException;
import game.city.Area;

import javax.swing.JPanel;

import java.awt.GridLayout;

import game.ui.gui.GameEvent;
import game.ui.gui.Triggerable;
import game.ui.gui.components.ImagePanel;
import game.ui.gui.panels.AreaSummaryPanel;
import game.city.Shop;
import game.city.VillainsLair;

import javax.swing.BoxLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class MainGamePanel extends JPanel implements Triggerable {

	/**
	 * 
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

	/**
	 * Create the application.
	 */
	public MainGamePanel(GameEnvironment env) {
		this.gameEnvironment = env;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setLayout(new BorderLayout(0, 0));
		
		JPanel northPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		teamSummaryPanel = new TeamSummaryPanel(getGameEnvironment().getTeam());
		northPanel.add(teamSummaryPanel);
		
		imagePanel = new ImagePanel();
		northPanel.add(imagePanel);
		
		navigationPanelHolder = new JPanel();
		northPanel.add(navigationPanelHolder);
		
		navigationPanel = new NavigationPanel(this, this.getGameEnvironment().getCityController());
		navigationPanelHolder.add(navigationPanel);
		
		areaSummaryPanel = new AreaSummaryPanel(this.getGameEnvironment().getCityController());
		add(areaSummaryPanel, BorderLayout.SOUTH);
		
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
		
	}
		
	private GameEnvironment getGameEnvironment() {
		return this.gameEnvironment;
	}
	
	private void updateNavigation() {
		
		// Prompt for confirmation before entering a Villain's Lair
		// TURNED OFF FOR TESTING!
//		if (getGameEnvironment().getCityController().getCurrentArea().getType() == AreaType.VILLAINS_LAIR) {
//			
//			int res = JOptionPane.showConfirmDialog(null, "You are about to enter a Villain's Lair. Are you sure you want to enter?", null, JOptionPane.YES_NO_OPTION);
//			
//			if (res != JOptionPane.YES_OPTION) {
//				
//				getGameEnvironment().getCityController().goTo(Direction.CENTRE);
//				
//			}
//			
//		}
		
		if (currentAreaPanel != null) {
			
			areaPanelHolder.remove(currentAreaPanel);
			currentAreaPanel = null;
			
		}
		
		// Set the currentAreaPanel depending on the type of area the team is in
		
		Area area = this.getGameEnvironment().getCityController().getCurrentArea();
		
		switch (area.getType()) {
		
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
				currentAreaPanel = new ShopAreaPanel(this, (Shop)area, this.getGameEnvironment().getTeam());
				break;
				
			case VILLAINS_LAIR:
				currentAreaPanel = new VillainsLairPanel(this, (VillainsLair)area, this.getGameEnvironment().getTeam());
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
	public void trigger(GameEvent event) {
		
		switch (event) {
		
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
					
				} catch (GameWonException e) {
					
					// TODO: Make more better
					JOptionPane.showMessageDialog(null, "Entire game has been won!");
					
				}
				
				break;
				
			default:
				throw new AssertionError(String.format("Could not handle event \"%s\".", event));
			
		}
	}
	
}
