package game.ui.gui.components;

import javax.swing.table.AbstractTableModel;

import game.item.HealingItem;
import game.item.Inventory;
import game.item.Item;
import game.item.ItemType;
import game.item.PowerUpItem;

public class InventoryTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4593903796514515122L;
	private ItemType filter;
	private boolean showPrices;
	private int numberOfColumns;
	
	private Item[] items;
	
	public InventoryTableModel(Inventory inventory, ItemType filter, boolean showPrices) {
		
		this.filter = filter;
		this.showPrices = showPrices;
		this.numberOfColumns = 2;
		
		if (showPrices) {
			
			this.numberOfColumns++;
			
		}
		
		if (filter == null) {
			
			this.items = inventory.getAllItems();
			
		} else {
			
			switch (filter) {
			
				case HEALING_ITEM:
					this.items = inventory.getHealingItems();
					this.numberOfColumns += 2;
					break;
					
				case MAP:
					this.items = inventory.getMaps();
					break;
					
				case POWER_UP_ITEM:
					this.items = inventory.getPowerUpItems();
					this.numberOfColumns += 2;
					break;
			
			}
			
		}
		
	}
	
	@Override
	public int getRowCount() {
		return this.items.length;
	}

	@Override
	public int getColumnCount() {
		return this.numberOfColumns;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		
		if (!showPrices) {
			
			columnIndex += 1;
			
		}
		
		switch (columnIndex) {
		
			case 0:
				return "Price";
				
			case 1:
				return "Type";
				
			case 2:
				return "Name";
				
			case 3:
				switch (filter) {
				
					case HEALING_ITEM:
						return "Restoration";
						
					case POWER_UP_ITEM:
						return "Ability";
						
					default:
						break;
				
				}
				break;
				
			case 4:
				switch (filter) {
				
					case HEALING_ITEM:
						return "Time";
						
					case POWER_UP_ITEM:
						return "Game";
						
					default:
						break;
				
				}
				break;
		
		}
		
		return "";
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Item item = this.items[rowIndex];
		
		if (!showPrices) {
			
			columnIndex += 1;
			
		}
		
		switch (columnIndex) {
		
			case 0:
				return String.format("$%d", item.getPrice());
				
			case 1:
				return item.getType().toString();
				
			case 2:
				return item.getName();
				
			case 3:
				switch (filter) {
				
					case HEALING_ITEM:
						return String.format("%d%%", ((HealingItem) item).getRestorationLevel() * 25);
						
					case POWER_UP_ITEM:
						return ((PowerUpItem)item).getAbility().getName();
						
					default:
						break;
				
				}
				break;
				
			case 4:
				
				switch (filter) {
				
					case HEALING_ITEM:
						return String.format("%ds", ((HealingItem) item).getApplicationTime());
						
					case POWER_UP_ITEM:
						return ((PowerUpItem)item).getAppliesTo();
						
					default:
						break;
				
				}
				
				break;
				
		}
		
		return "";
		
	}
	
	@Override
	public boolean isCellEditable(int x, int y) {
		return false;
	}
	
	public Item getSelectedItem(int row) {
		
		try {
			
			return this.items[row];
			
		} catch (Exception e) {
			
			return null;
			
		}
		
	}

}
