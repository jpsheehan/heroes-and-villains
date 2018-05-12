package game.ui.gui.panels;

import javax.swing.JPanel;

import game.city.CityController;
import game.item.Inventory;
import game.item.ItemType;
import game.item.Map;
import game.ui.gui.DialogResult;
import game.ui.gui.Triggerable;
import game.ui.gui.dialogs.ItemSelectionDialog;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeBasePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7383141118407290522L;

	/**
	 * Create the panel.
	 */
	public HomeBasePanel(Triggerable window, Inventory inventory, CityController cityController) {
		
		JButton btnUseAMap = new JButton("Use a Map...");
		btnUseAMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(inventory, ItemType.MAP);
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					if (dlg.getSelectedItem() == null) {
						
						throw new AssertionError();
						
					}
					
					Map map = (Map)dlg.getSelectedItem();
					
					JOptionPane.showMessageDialog(null, String.format("You used a %s to reveal all the areas in %s!", map.getName(), cityController.getCurrentCity().getName()));
					
					cityController.useMap(map);
					inventory.remove(map);
					
					window.triggerUpdateNavigation();
					
				}
				
			}
		});
		add(btnUseAMap);
		
	}

}
