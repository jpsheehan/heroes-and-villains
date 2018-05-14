package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;

import game.item.Inventory;
import game.item.Item;
import game.item.ItemType;

import game.ui.gui.components.InventoryPanel;

public class ItemSelectionDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348976844662470541L;
	private DialogResult dialogResult;
	private InventoryPanel inventoryPanel;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		//for testing
//	    City city = new City(CityType.ERSKINE);
//	    Inventory inventory = ((Shop)city.getArea(AreaType.SHOP)).getInventory();
//	    
//	  //Testing: ItemType = null, should get all items in inventory and showPrice = true, should display prices
//		try {
//			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, null, true);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			
//			if (dialog.getDialogResult() == DialogResult.OK) {
//				System.out.println(dialog.getSelectedItem().getName());
//			}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	    
//		//Testing: ItemType = null, should get all items in inventory and showPrice = false, should not display prices
//				try {
//					ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, null, false);
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//					
//					if (dialog.getDialogResult() == DialogResult.OK) {
//						System.out.println(dialog.getSelectedItem().getName());
//					}
//
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//		
//	    //Testing Type HealingItem
//		try {
//			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.HEALING_ITEM, true);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			
//			if (dialog.getDialogResult() == DialogResult.OK) {
//				System.out.println(dialog.getSelectedItem().getName());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		//Testing Type MapItem
//		try {
//			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.MAP, true);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//
//			if (dialog.getDialogResult() == DialogResult.OK) {
//				System.out.println(dialog.getSelectedItem().getName());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//				
//		//Testing Type PowerUpItem
//		try {
//			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.POWER_UP_ITEM, true);
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//			
//
//			if (dialog.getDialogResult() == DialogResult.OK) {
//				System.out.println(dialog.getSelectedItem().getName());
//			}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	}

	/**
	 * Create the dialog
	 * Dialog only displays Items of the specified ItemType from the inventory
	 * If Boolean is set to false, price is not shown
	 * @param Inventory, ItemType, Boolean
	 */
	public ItemSelectionDialog(Inventory inventory, ItemType itemType, boolean showPrice, boolean showOkButton) {
		// DefaultListModel<String> listModel = new DefaultListModel<String>();

		setResizable(false);
		setModal(true);
		setTitle("Item Selection Menu");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
//				if (itemType == null) {
//					if (showPrice == false) {
//					
//						// show all the items without price
//						for (Item item : inventory.getAllItems()) {
//						
//							listModel.addElement(String.format("%s: (%s) %s", item.getName(), item.getFlavourText(), item.getType().toString()));
//						
//						}
//					}
//					else {
//						// show all the items without price
//						for (Item item : inventory.getAllItems()) {
//						
//							listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));
//						
//						}
//					}	
//				} 
//				else {
//					
//					switch (itemType) {
//						
//						case MAP:
//							// Get the item details from the inventory into a string array			
//							for (Item item : inventory.getMaps()) {
//								listModel.addElement(String.format("%s: (%s) %s", item.getName(), item.getFlavourText(), item.getType().toString()));		
//								}
//							break;
//							
//						case HEALING_ITEM:
//							for (Item item : inventory.getHealingItems()) {
//								listModel.addElement(String.format("%s: (%s) %s", item.getName(), item.getFlavourText(), item.getType().toString()));		
//								}
//							break;
//							
//						case POWER_UP_ITEM:
//								for (Item item : inventory.getPowerUpItems()) {
//								listModel.addElement(String.format("%s: (%s) %s", item.getName(), item.getFlavourText(), item.getType().toString()));		
//								}
//							break;
//							
//						default:
//							throw new AssertionError();
//							
//					}
//					
//				}
				
			}
			
		});
		
		setBounds(100, 100, 639, 429);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		{
			inventoryPanel = new InventoryPanel(inventory, itemType, showPrice);
			panel.add(inventoryPanel, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			if (showOkButton) {
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setDialogResult(DialogResult.OK);
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						setDialogResult(DialogResult.CANCEL);
						dispose();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
	}
	
	/**
	 * Sets the result of this dialog.
	 * @param dialogResult
	 */
	private void setDialogResult(DialogResult dialogResult) {
		
		this.dialogResult = dialogResult;	
	}
	
	/**
	 * Gets the result of this dialog.
	 * @return
	 */
	public DialogResult getDialogResult() {
		
		return this.dialogResult;
	}
	/**
	 * Gets the result of this Item Selection.
	 * @return
	 */
	public Item getSelectedItem() {
		
		return inventoryPanel.getSelectedItem();
	}
	
}
