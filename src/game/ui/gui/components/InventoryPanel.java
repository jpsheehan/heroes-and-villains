package game.ui.gui.components;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.item.Inventory;
import game.item.Item;
import game.item.ItemType;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

/**
 * version 0.3
 * @author manuh
 * 
 * @version 1.0
 * @author manuh
 * Adds ItemType and Boolean to allow for display of different Item Types and hiding / showing price
 */
public class InventoryPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private Item selectedItem;
	private JLabel lblDescription;
	
	/**
	 * Create the panel.
	 * @param inventory The inventory of the team visiting the shop
	 * @param filter The type of item to filter by
	 * @param showPrices Whether or not to show prices.
	 */
	public InventoryPanel(Inventory inventory, ItemType filter, boolean showPrices) {
		
		InventoryTableModel model = new InventoryTableModel(inventory, filter, showPrices);
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				
				selectedItem = model.getSelectedItem(table.getSelectedRow());
				
				if (selectedItem != null) {
					lblDescription.setText(selectedItem.getFlavourText());
				}
				
			}
		});
		
		int effectsColumnWidth = 300;
		
		if (filter == null) {
			
			if (showPrices) {
				
				table.getColumnModel().getColumn(0).setPreferredWidth(20);
				table.getColumnModel().getColumn(3).setPreferredWidth(effectsColumnWidth);
				
			} else {
				
				table.getColumnModel().getColumn(2).setPreferredWidth(effectsColumnWidth);
				
			}
		
		}
		
		add(new JScrollPane(table));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		lblDescription = new JLabel("");
		panel.add(lblDescription);
		
		if (model.getRowCount() > 0) {
			
			table.changeSelection(0, 0, false, false);
			
		}
		
	}
	
	public Item getSelectedItem() {
		
		return selectedItem;
		
	}

}
