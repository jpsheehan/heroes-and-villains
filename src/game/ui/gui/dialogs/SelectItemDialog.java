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
import javax.swing.border.EmptyBorder;

import game.character.HeroType;
import game.city.*;
//import game.city.CityController;
//import game.city.City;
//import game.city.Shop;
import game.item.Item;
import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JComboBox;

public class SelectItemDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -111727830559469912L;
	private DialogResult result;
	//private jList<Item> listItems;
	//private String itemName;
	//private String itemType;
	//private int itemPrice;
	//String[] itemNames = new String[CityController.getCurrentCity().Shop.getInventory().size()];
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectItemDialog dialog = new SelectItemDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectItemDialog() {
		
		setResizable(false);
		setModal(true);
		setTitle("Select an Item");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				//Create string array to hold item info, length = number of items in the shop inventory  ,will go onto the combo box,
				String[] itemNames = new String[CityController.Area.Shop.getInventory().size()];
				
			
				// Populate the combo box list with the Items, their flavour / description, type (healing, ability, map), and price
				int i = 0;
				for (Item item : shop.getInventory().getAllItems()) {
							
					itemNames[i++] = String.format("$%d - %s (%s): %s", item.getPrice(), item.getName(), item.getType().toString(), item.getFlavourText());
							
					}
				/*
				for (Shop counter : Shop.getInventory().values()) {
					
					comboBoxListItems.addItem(counter.getName());
					
				}
				
				*/
						
				comboBoxListItems.setSelectedItem(Shop.getInventory().values()[0]);
				
				itemType = ItemType.values()[0];
				updateLabels();
				
			}
		});
		
		setBounds(100, 100, 450, 320);
		getContentPane().setLayout(null);
		
		JLabel itemLbl = new JLabel("Item");
		itemLbl.setBounds(0, 12, 70, 15);
		getContentPane().add(itemLbl);
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setBounds(378, 12, 70, 15);
		getContentPane().add(priceLbl);
		
		JLabel descriptionLbl = new JLabel("Description");
		descriptionLbl.setBounds(108, 12, 96, 15);
		getContentPane().add(descriptionLbl);
		
		JLabel itemTypeLbl = new JLabel("Item Type");
		itemTypeLbl.setBounds(287, 12, 70, 15);
		getContentPane().add(itemTypeLbl);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 259, 448, 35);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
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
		
		JComboBox<String> comboBoxListItems = new JComboBox<String>();
		comboBoxListItems.setBounds(0, 26, 448, 234);
		getContentPane().add(comboBoxListItems);
	}
	
	@Override
	public DialogResult getDialogResult() {
		
		return this.result;
		
	}
	
	private void setDialogResult(DialogResult result) {
		
		this.result = result;
		
	}
	
}
