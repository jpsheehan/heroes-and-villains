package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 5348976844662470541L;
	private DialogResult dialogResult;
	private InventoryPanel inventoryPanel;

	/**
	 * Create the dialog
	 * Dialog only displays Items of the specified ItemType from the inventory
	 * If Boolean is set to false, price is not shown
	 * @param Inventory, ItemType, Boolean
	 */
	public ItemSelectionDialog(Inventory inventory, ItemType itemType, boolean showPrice, boolean showOkButton) {

		setResizable(false);
		setModal(true);
		setTitle("Item Selection Menu");
		
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
