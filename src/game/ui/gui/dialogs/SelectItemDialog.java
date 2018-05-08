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
import game.city.Shop;
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
	private String itemName;
	private String itemType;
	private int itemPrice;
		
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
				
				// Populate the combo box with the Items, their flavour / description, type (healing, ability, map), and price
				for (Shop counter : Shop.getInventory().values()) {
					
					comboCoxListItems.addItem(counter.getName());
					
				}
				
				comboCoxListItems.setSelectedItem(Shop.getInventory().values()[0]);
				
				itemType = ItemType.values()[0];
				updateLabels();
				
			}
		});
		
		setBounds(100, 100, 450, 320);
		getContentPane().setLayout(null);
		
		JLabel Item = new JLabel("New label");
		Item.setBounds(0, 12, 70, 15);
		getContentPane().add(Item);
		
		JLabel Price = new JLabel("New label");
		Price.setBounds(378, 12, 70, 15);
		getContentPane().add(Price);
		
		JLabel Description = new JLabel("New label");
		Description.setBounds(108, 12, 70, 15);
		getContentPane().add(Description);
		
		JLabel ItemType = new JLabel("New label");
		ItemType.setBounds(287, 12, 70, 15);
		getContentPane().add(ItemType);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 259, 448, 35);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("OK");
		button.setActionCommand("OK");
		buttonPane.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setActionCommand("Cancel");
		buttonPane.add(button_1);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					setDialogResult(DialogResult.OK);
					dispose();
					
				}
			});
			{
				JButton btnNewButton = new JButton("New button");
				buttonPane.add(btnNewButton);
			}
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
		
		JComboBox comboCoxListItems = new JComboBox();
		comboCoxListItems.setBounds(0, 26, 448, 234);
		getContentPane().add(comboCoxListItems);
	}
	
	@Override
	public DialogResult getDialogResult() {
		
		return this.result;
		
	}
	
	private void setDialogResult(DialogResult result) {
		
		this.result = result;
		
	}
	
}
