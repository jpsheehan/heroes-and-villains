package game.ui.gui.panels.areas;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import game.character.HeroType;
import game.city.CityController;
import game.item.ItemType;
import game.item.Map;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.ItemSelectionDialog;
import game.ui.gui.windows.GameWindow;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class HomeBasePanel extends GenericAreaPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 7383141118407290522L;

	/**
	 * Create the panel.
	 */
	public HomeBasePanel(GameEventListener window, Team team, CityController cityController) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblYouCanUse = new JLabel("You can use a map here to view the whole building!");
		panel.add(lblYouCanUse);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JButton btnUseAMap = new JButton("Use a Map...");
		panel_1.add(btnUseAMap);
		
		btnUseAMap.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if (cityController.hasUsedMap()) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), "You have already used a map in this building!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				Hero compSciHero = null;
				for (Hero hero : team.getHeroes()) {
					if (hero.getType() == HeroType.COMPUTER_SCIENCE_STUDENT) {
						compSciHero = hero;
						break;
					}
				}
				
				if (compSciHero != null) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("%s used their hacker abilities to break into the game's code and reveal the entire map of %s!", compSciHero.getName(), cityController.getCurrentCity().getName()), "Information", JOptionPane.INFORMATION_MESSAGE);
					cityController.useMap(new Map("Fake Map", "Not real", 0));
					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
					update();
					return;
					
				}
				
				if (team.getInventory().getMaps().length == 0) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), "You don't have any maps to choose from.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(team.getInventory(), ItemType.MAP, false, true);
				dlg.setLocationRelativeTo(GameWindow.getMainWindow());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					if (dlg.getSelectedItem() == null) {
						
						throw new AssertionError();
						
					}
					
					Map map = (Map)dlg.getSelectedItem();
					
					cityController.useMap(map);
					team.getInventory().remove(map);
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("You used a %s to reveal all the areas in %s!", map.getName(), cityController.getCurrentCity().getName()), "Success", JOptionPane.INFORMATION_MESSAGE);

					window.gameEventPerformed(new GameEvent(GameEventType.NAVIGATION_CHANGED));
					
					update();
					
				}
				
			}
		});
		
	}

	@Override
	public void update() {
		
		revalidate();
		repaint();
		
	}

}
