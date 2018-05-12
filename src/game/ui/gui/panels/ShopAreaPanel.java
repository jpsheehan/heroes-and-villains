package game.ui.gui.panels;

import javax.swing.JPanel;

import game.Team;
import game.city.Shop;
import game.item.Item;
import game.ui.gui.DialogResult;
import game.ui.gui.Triggerable;
import game.ui.gui.dialogs.ItemSelectionDialog;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShopAreaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7862077770649661135L;

	private Triggerable window;
	private Shop shop;
	private Team team;
	private JTextArea textAreaDialogue;
	private JButton btnBuyItems;
	
	/**
	 * Create the panel.
	 */
	public ShopAreaPanel(Triggerable window, Shop shop, Team team) {

		this.window = window;
		this.shop = shop;
		this.team = team;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Inn Keeper Name:");
		panel_1.add(lblNewLabel);
		
		JLabel lblInnKeeperName = new JLabel("<lblInnKeeperName>");
		panel_1.add(lblInnKeeperName);
		
		JPanel panel = new JPanel();
		add(panel);
		
		textAreaDialogue = new JTextArea();
		textAreaDialogue.setLineWrap(true);
		textAreaDialogue.setWrapStyleWord(true);
		textAreaDialogue.setBackground(UIManager.getColor("Label.background"));
		panel.add(textAreaDialogue);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		btnBuyItems = new JButton("Buy Items...");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(shop.getInventory());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					Item item = dlg.getSelectedItem();
					
					if (item == null) {
						
						throw new AssertionError("ItemSelectionDialog returned OK but getSelectedItem was null");
						
					}
					
					try {
						
						team.spendMoney(item.getPrice());
						
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, "You don't have enough money for that.");
						
						return;
					}
					
					// add the item to the inventory
					team.getInventory().add(item);
					
					updateDialogue(String.format(shop.getInnKeeper().getDialogue().getPurchase()));
					
					update();
					
				}
				
			}
		});
		panel_2.add(btnBuyItems);
		
		if (this.shop == null || this.team == null) {
			
			lblInnKeeperName.setText("NULL");
			textAreaDialogue.setText("NULL");
			btnBuyItems.setEnabled(false);
			
		} else {
			
			lblInnKeeperName.setText(shop.getInnKeeper().getName());
			updateDialogue(shop.getInnKeeper().getDialogue().getGreeting());
			btnBuyItems.setEnabled(true);
			
		}
		
		update();
		
	}
	
	public void update() {
		
		if (this.shop != null && this.team != null) {
			
			if (shop.getInventory().size() == 0) {
				
				btnBuyItems.setEnabled(false);
				
			} else {
				
				btnBuyItems.setEnabled(true);
				
			}
			
		}
		
	}
	
	private void updateDialogue(String str) {
		
		this.textAreaDialogue.setText(str);
		
	}

}
