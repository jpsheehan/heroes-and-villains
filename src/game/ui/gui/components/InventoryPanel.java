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

public class InventoryPanel extends JPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private Item selectedItem;
	
	/**
	 * Create the panel.
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
				
			}
		});
		
		add(new JScrollPane(table));
		
		if (model.getRowCount() > 0) {
			
			table.changeSelection(0, 0, false, false);
			
		}
		
	}
	
	public Item getSelectedItem() {
		
		return selectedItem;
		
	}

}
