package game.ui.gui.panels.areas;

import game.Team;
import game.character.Hero;
import game.item.ItemType;
import game.item.PowerUpItem;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.dialogs.ItemSelectionDialog;
import game.ui.gui.windows.GameWindow;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PowerUpDenPanel extends GenericAreaPanel {

	private Hero selectedHero;
	private PowerUpItem selectedItem;
	
	private JButton btnApplyPowerUp;
	private JLabel lblSelectedHero, lblSelectedItem;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -6790779228677501246L;

	/**
	 * Create the panel.
	 */
	public PowerUpDenPanel(GameEventListener window, Team team) {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblYouCanApply = new JLabel("You can apply power up items to heroes here.");
		panel.add(lblYouCanApply);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Selected Hero:");
		panel_1.add(lblNewLabel);
		
		lblSelectedHero = new JLabel("lblSelectedHero");
		panel_1.add(lblSelectedHero);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		JButton btnSelectAHero = new JButton("Select a Hero...");
		btnSelectAHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HeroSelectionDialog dlg = new HeroSelectionDialog(team.getHeroes());
				dlg.setLocationRelativeTo(GameWindow.getMainWindow());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					selectedHero = dlg.getSelectedHero();
					
					update();
					
				}
				
			}
		});
		panel_2.add(btnSelectAHero);
		
		JPanel panel_3 = new JPanel();
		add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Selected Item:");
		panel_3.add(lblNewLabel_1);
		
		lblSelectedItem = new JLabel("lblSelectedItem");
		panel_3.add(lblSelectedItem);
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		
		JButton btnSelectAnItem = new JButton("Select an Item...");
		btnSelectAnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (team.getInventory().getPowerUpItems().length == 0) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), "You don't have any power up items to choose from.", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					ItemSelectionDialog dlg = new ItemSelectionDialog(team.getInventory(), ItemType.POWER_UP_ITEM, false, true);
					dlg.setLocationRelativeTo(GameWindow.getMainWindow());
					dlg.setVisible(true);
					
					if (dlg.getDialogResult() == DialogResult.OK) {
						
						selectedItem = (PowerUpItem) dlg.getSelectedItem();
						
						update();
						
					}
					
				}
				
			}
		});
		panel_4.add(btnSelectAnItem);
		
		JPanel panel_5 = new JPanel();
		add(panel_5);
		
		btnApplyPowerUp = new JButton("Apply Power Up Item");
		btnApplyPowerUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				
				if (selectedHero.hasPowerUpItem()) {
				
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("%s is already holding a power up item!", selectedHero.getName()), "Error", JOptionPane.INFORMATION_MESSAGE);
					return;
					
				}
				
				selectedHero.applyPowerUpItem(selectedItem);
				team.getInventory().remove(selectedItem);

				JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("%s is holding %s!", selectedHero.getName(), selectedItem.getName()), "Success", JOptionPane.INFORMATION_MESSAGE);
				
				selectedItem = null;
				selectedHero = null;
				update();

			}
		});
		panel_5.add(btnApplyPowerUp);
		
		update();
		
	}

	@Override
	public void update() {
		
		if (selectedHero == null) {
			
			lblSelectedHero.setText("None");
			
		} else {
			
			lblSelectedHero.setText(selectedHero.getName());
			
		}
		
		if (selectedItem == null) {
			
			lblSelectedItem.setText("None");
			
		} else {
			
			lblSelectedItem.setText(selectedItem.getName());
			
		}
		
		if (selectedHero != null && selectedItem != null) {
			
			btnApplyPowerUp.setEnabled(true);
			
		} else {
			
			btnApplyPowerUp.setEnabled(false);
			
		}
	}

}
