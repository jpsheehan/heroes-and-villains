package game.ui.gui.panels.areas;

import javax.swing.JPanel;

import game.Team;
import game.city.Shop;
import game.item.Item;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEvent;
import game.ui.gui.GameEventType;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.ItemSelectionDialog;
import game.ui.gui.windows.GameWindow;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

/**
 * Implements the GUI interface for the Shop area
 * @author jesse
 *
 */
public class ShopAreaPanel extends GenericAreaPanel {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 7862077770649661135L;

	private Shop shop;
	private Team team;
	private JLabel textAreaDialogue;
	private JButton btnBuyItems;
	
	/**
	 * Create the panel.
	 * @param window The parent event listener.
	 * @param shop The shop this area is based on.
	 * @param team The team that is visiting the shop.
	 */
	public ShopAreaPanel(GameEventListener window, Shop shop, Team team) {
		
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
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textAreaDialogue = new JLabel();
		panel.add(textAreaDialogue);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		btnBuyItems = new JButton("Buy Items...");
		btnBuyItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(shop.getInventory(), null, true, true);
				dlg.setLocationRelativeTo(GameWindow.getMainWindow());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					Item item = dlg.getSelectedItem();
					
					if (item == null) {
						
						throw new AssertionError("ItemSelectionDialog returned OK but getSelectedItem was null");
						
					}
					
					try {
						
						team.spendMoney(item.getPrice());
						
						// add the item to the inventory
						team.getInventory().add(item);
						
						update();
						
						// Update the funds in the window
						window.gameEventPerformed(new GameEvent(GameEventType.TEAM_CHANGED));
						
						JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format(shop.getInnKeeper().getDialogue().getPurchase(), item.getName()), "Success", JOptionPane.INFORMATION_MESSAGE);
						
					} catch (Exception e2) {
						
						JOptionPane.showMessageDialog(GameWindow.getMainWindow(), "You don't have enough money for that.", "Error", JOptionPane.ERROR_MESSAGE);
						
					}
					
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
		
		repaint();
		
		if (this.shop != null && this.team != null) {
			
			if (shop.getInventory().size() == 0) {
				
				btnBuyItems.setEnabled(false);
				
			} else {
				
				btnBuyItems.setEnabled(true);
				
			}
			
		}
		
	}
	
	private void updateDialogue(String str) {
		
		this.textAreaDialogue.setText("<html>" + str + "</html>");
		
	}
}
