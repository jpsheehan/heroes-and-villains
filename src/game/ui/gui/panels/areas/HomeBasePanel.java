package game.ui.gui.panels.areas;

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
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class HomeBasePanel extends GenericAreaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7383141118407290522L;

	/**
	 * Create the panel.
	 */
	public HomeBasePanel(Triggerable window, Inventory inventory, CityController cityController) {
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
				
				if (inventory.getMaps().length == 0) {
					
					JOptionPane.showMessageDialog(null, "You don't have any maps to use.");
					return;
					
				}
				
				if (cityController.hasUsedMap()) {
					
					JOptionPane.showMessageDialog(null, "You have already used a map in this building!");
					return;
					
				}
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(inventory, ItemType.MAP, false, true);
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					if (dlg.getSelectedItem() == null) {
						
						throw new AssertionError();
						
					}
					
					Map map = (Map)dlg.getSelectedItem();
					
					cityController.useMap(map);
					inventory.remove(map);
					
					JOptionPane.showMessageDialog(null, String.format("You used a %s to reveal all the areas in %s!", map.getName(), cityController.getCurrentCity().getName()));
					
					window.triggerUpdateNavigation();
					
				}
				
			}
		});
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		repaint();
	}

}
