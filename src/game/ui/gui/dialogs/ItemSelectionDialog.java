package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;

import game.city.*;
import game.item.Inventory;
import game.item.Item;
import game.item.ItemType;

import javax.swing.JList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class ItemSelectionDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348976844662470541L;
	private DialogResult dialogResult;
	private int index;
	private Item selectedItem;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//for testing
	    City city = new City(CityType.ERSKINE);
	    Inventory inventory = ((Shop)city.getArea(AreaType.SHOP)).getInventory();
	    
	  //Testing default get all items in inventory
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			if (dialog.getDialogResult() == DialogResult.OK) {
				System.out.println(dialog.getSelectedItem().getName());
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
	    
	    //Testing Type HealingItem
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.HEALING_ITEM);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			if (dialog.getDialogResult() == DialogResult.OK) {
				System.out.println(dialog.getSelectedItem().getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Testing Type MapItem
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.MAP);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

			if (dialog.getDialogResult() == DialogResult.OK) {
				System.out.println(dialog.getSelectedItem().getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
				
		//Testing Type PowerUpItem
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory, ItemType.POWER_UP_ITEM);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			

			if (dialog.getDialogResult() == DialogResult.OK) {
				System.out.println(dialog.getSelectedItem().getName());
			}

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * Create the dialog
	 * Dialog only displays Items of the specified ItemType from the inventory
	 * @param Inventory, ItemType
	 * @override
	 */
	public ItemSelectionDialog(Inventory inventory, ItemType itemType) {
		DefaultListModel<String> listModel = new DefaultListModel<String>();

		setResizable(false);
		setModal(true);
		setTitle("Item Selection Menu");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				if (itemType == null) {
					
					// show all the items
					for (Item item : inventory.getAllItems()) {
						
						listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));
						
					}
					
				} else {
					
					switch (itemType) {
						
						case MAP:
							// Get the item details from the inventory into a string array			
							for (Item item : inventory.getMaps()) {
								listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));		
								}
							break;
							
						case HEALING_ITEM:
							for (Item item : inventory.getHealingItems()) {
								listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));		
								}
							break;
							
						case POWER_UP_ITEM:
								for (Item item : inventory.getPowerUpItems()) {
								listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));		
								}
							break;
							
						default:
							throw new AssertionError();
							
					}
					
				}
				
			}
			
		});
		
		setBounds(100, 100, 639, 429);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("left:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				ColumnSpec.decode("right:default"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		{
			JLabel lblNewLabel = new JLabel("Item");
			panel.add(lblNewLabel, "3, 2");
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Description");
			panel.add(lblNewLabel_2, "5, 2");
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Type");
			panel.add(lblNewLabel_3, "16, 2");
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Price");
			panel.add(lblNewLabel_1, "22, 2");
		}
		{
			JList<String> list = new JList<String>(listModel);
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					try {	
					index = list.getSelectedIndex();					
					selectedItem = inventory.getAllItems()[index];
				} catch (Exception e1) {
					// SelectedItem is null
				}
				}
			});
			panel.add(list, "2, 4, 22, 1, fill, fill");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
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
		
		return this.selectedItem;
	}
}
