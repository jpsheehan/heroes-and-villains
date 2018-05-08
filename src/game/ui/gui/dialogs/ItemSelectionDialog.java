package game.ui.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.ui.gui.DialogResult;
import game.ui.gui.Returnable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ItemSelectionDialog extends JDialog implements Returnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5348976844662470541L;
	//private ItemType itemType;
	private String itemName;
	private DialogResult dialogResult;

	private final JPanel contentPanel = new JPanel();
	private JLabel lblItemName;
	private JLabel lblItemFlavourText;
	private JLabel lblItemPrice;
	private JComboBox<String> comboBoxItem;
	private Color defaultTextFieldBackgroundColor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemSelectionDialog dialog = new ItemSelectionDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ItemSelectionDialog() {
		setResizable(false);
		setModal(true);
		setTitle("Select Items");
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				// Populate the combo box with Items
				for (HeroType type : HeroType.values()) {
					
					comboBoxItem.addItem(type.getName());
					
				}
				
				comboBoxItem.setSelectedItem(HeroType.values()[0]);
				defaultTextFieldBackgroundColor = textFieldHeroName.getBackground();
				
				heroType = HeroType.values()[0];
				updateLabels();
				
			}
		});
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			JLabel lblItemName = new JLabel("Item:");
			contentPanel.add(lblItemName, "2, 2, right, default");
		}
		
		{
			JLabel lblItemFlavourText = new JLabel("Description:");
			contentPanel.add(lblItemFlavourText, "2, 4, default, top");
		}
		
		{
			JLabel lblItemPrice = new JLabel("Price:");
			contentPanel.add(lblItemPrice, "2, 4, default, top");
		}
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(6, 6, 438, 233);
		contentPanel.add(comboBox);
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
