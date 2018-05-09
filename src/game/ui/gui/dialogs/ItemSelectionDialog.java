package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import game.character.HeroType;
import game.city.*;
import game.item.Inventory;
import game.item.Item;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ItemSelectionDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348976844662470541L;
	private DialogResult dialogResult;
	private String itemName;
	private int index;
	private JList<String> listItems;
	private final JPanel contentPanel = new JPanel();
	private Inventory inventory;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//for testing
	    City city = new City(CityType.ERSKINE);
	    Inventory inventory = ((Shop)city.getArea(AreaType.SHOP)).getInventory();
	    //(new ItemSelectionDialog(inventory)).setVisible(true);
	    
	    //
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog(inventory);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ItemSelectionDialog(Inventory inventory) {
		this.inventory = inventory;
		DefaultListModel<String> listModel = new DefaultListModel<>();

		setResizable(false);
		setModal(true);
		setTitle("Item Selection Menu");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				// Get the item details from the inventory into a string array			
				for (Item item : inventory.getAllItems()) {
					
					listModel.addElement(String.format("%s: (%s) %s $%d", item.getName(), item.getFlavourText(), item.getType().toString(), item.getPrice()));		
					
				}
				
			}
		});
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(6, 0, 31, 27);
		contentPanel.add(lblItem);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(388, 0, 48, 27);
		contentPanel.add(lblPrice);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(86, 0, 88, 27);
		contentPanel.add(lblDescription);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(312, 0, 34, 27);
		contentPanel.add(lblType);
		
		listItems = new JList<>(listModel);
		getContentPane().add(listItems, BorderLayout.CENTER);			//if enabled it overwrites the labels
		//add(new JScrollPane(listItems));
		listItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listItems.setBounds(0, 238, 448, -213);
		//contentPanel.add(listItems);									//if enabled there are labels but no list
		listItems.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				
				//String nameOfItem = listItems.getSelectedValue();
				index = listItems.getSelectedIndex();
				itemName = listModel.get(index);
			}
		});
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
}
