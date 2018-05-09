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
import javax.swing.BoxLayout;
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
	private String itemName;
	private int index;
	private JList<String> listItems;
	// private final JPanel contentPanel = new JPanel();
	private Inventory inventory;
	private Item selectedItem;
	
	
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
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FormLayout(new ColumnSpec[] {},
			new RowSpec[] {}));
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
//				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
//				ColumnSpec.decode("31px"),
//				ColumnSpec.decode("35px"),
//				ColumnSpec.decode("81px"),
//				ColumnSpec.decode("34px"),
//				ColumnSpec.decode("1px"),},
//			new RowSpec[] {
//				RowSpec.decode("104px"),
//				RowSpec.decode("15px"),
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,
//				FormSpecs.RELATED_GAP_ROWSPEC,
//				FormSpecs.DEFAULT_ROWSPEC,}));
//		
//		JLabel lblItem = new JLabel("Item");
//		contentPanel.add(lblItem, "2, 1, left, center");
//		
//		JLabel lblPrice = new JLabel("Price");
//		contentPanel.add(lblPrice, "2, 2, left, center");
//		
//		JLabel lblDescription = new JLabel("Description");
//		contentPanel.add(lblDescription, "2, 4, left, center");
//		
//		JLabel lblType = new JLabel("Type");
//		contentPanel.add(lblType, "2, 6, left, center");
//		
//		listItems = new JList<>(listModel);
//		contentPanel.add(listItems, "2, 8, 4, 1");
//		//add(new JScrollPane(listItems));
//		listItems.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//		listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		//contentPanel.add(listItems);									//if enabled there are labels but no list
//		listItems.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent arg0) {
//				
//				//String nameOfItem = listItems.getSelectedValue();
//				index = listItems.getSelectedIndex();
//				// itemName = listModel.get(index);
//				
//				selectedItem = inventory.getAllItems()[index];
//				
//			}
//		});
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
