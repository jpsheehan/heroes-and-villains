package game.ui.gui.panels;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import game.city.AreaType;
import game.city.CityController;
import game.city.Direction;
import game.city.IllegalMoveException;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventListener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 1933279948591949043L;
	
	private MapPanel mapPanel;
	private CityController cityController;
	private JButton btnNorth, btnSouth, btnEast, btnWest;

	/**
	 * Create the panel.
	 * @param window Containing panel
	 * @param cityController Current CityController of the game
	 */
	public NavigationPanel(GameEventListener window, CityController cityController) {
		
		this.cityController = cityController;
		
		setLayout(new BorderLayout(0, 0));
		
		mapPanel = new MapPanel(cityController);
		this.add(mapPanel, BorderLayout.CENTER);
		mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));
		
		btnNorth = new JButton("N");
		btnNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cityController.move(Direction.NORTH);
					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
				} catch (IllegalMoveException e1) {}
			}
		});
		this.add(btnNorth, BorderLayout.NORTH);
		
		btnSouth = new JButton("S");
		btnSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cityController.move(Direction.SOUTH);
					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
				} catch (IllegalMoveException e1) {}
			}
		});
		this.add(btnSouth, BorderLayout.SOUTH);
		
		btnWest = new JButton("W");
		btnWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cityController.move(Direction.WEST);
					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
				} catch (IllegalMoveException e1) {}
			}
		});
		this.add(btnWest, BorderLayout.WEST);
		
		btnEast = new JButton("E");
		btnEast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cityController.move(Direction.EAST);
					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
				} catch (IllegalMoveException e1) {}
			}
		});
		this.add(btnEast, BorderLayout.EAST);

	}
	
	public void update() {
		
		mapPanel.repaint();
		
		btnNorth.setEnabled(false);
		btnSouth.setEnabled(false);
		btnWest.setEnabled(false);
		btnEast.setEnabled(false);
		
		if (cityController.getCurrentArea().getType() == AreaType.VILLAINS_LAIR) {
			return;
		}
		
		switch (cityController.getDirection()) {
		
			case EAST:
				btnWest.setEnabled(true);
				break;
				
			case WEST:
				btnEast.setEnabled(true);
				break;
				
			case NORTH:
				btnSouth.setEnabled(true);
				break;
				
			case SOUTH:
				btnNorth.setEnabled(true);
				break;
				
			case CENTRE:
				btnNorth.setEnabled(true);
				btnSouth.setEnabled(true);
				btnWest.setEnabled(true);
				btnEast.setEnabled(true);
				break;
		
		}
		
	}

}
