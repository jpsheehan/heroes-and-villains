package game.ui.gui.components;

import javax.swing.JPanel;
import javax.swing.JTable;

import game.item.Inventory;
import game.item.ItemType;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class InventoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public InventoryPanel(Inventory inventory, ItemType filter, boolean showPrices) {
		
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new InventoryTableModel(inventory, filter, showPrices));
		
		add(new JScrollPane(table));

	}

}
