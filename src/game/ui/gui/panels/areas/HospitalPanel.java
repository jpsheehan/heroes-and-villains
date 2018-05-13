package game.ui.gui.panels.areas;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.Triggerable;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.dialogs.ItemSelectionDialog;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import game.item.HealingItem;
import game.item.ItemType;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class HospitalPanel extends GenericAreaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6330847966216959076L;
	
	private Hero selectedHero;
	private HealingItem selectedItem;
	
	private JButton btnApplyHealingItem;
	private JLabel lblSelectedItem, lblSelectedHero;

	/**
	 * Create the panel.
	 */
	public HospitalPanel(Triggerable window, Team team) {

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel_6 = new JPanel();
		add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel_6.add(panel);
		
		JLabel lblThisIsA = new JLabel("You can apply healing items here.");
		panel.add(lblThisIsA);
		
		JPanel panel_1 = new JPanel();
		
		JButton btnSelectAHero = new JButton("Select a Hero...");
		btnSelectAHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HeroSelectionDialog dlg = new HeroSelectionDialog(team.getHeroes());
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					selectedHero = dlg.getSelectedHero();
					
					update();
					
				}
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_6.add(panel_2);
		
		JLabel lblLeadingHero = new JLabel("Selected Hero:");
		panel_2.add(lblLeadingHero);
		
		lblSelectedHero = new JLabel("lblSelectedHero");
		panel_2.add(lblSelectedHero);
		panel_1.add(btnSelectAHero);
		panel_6.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_6.add(panel_3);
		
		JLabel lblSelectedItem_1 = new JLabel("Selected Item:");
		panel_3.add(lblSelectedItem_1);
		
		lblSelectedItem = new JLabel("lblSelectedItem");
		panel_3.add(lblSelectedItem);
		
		JPanel panel_4 = new JPanel();
		panel_6.add(panel_4);
		
		JButton btnSelectAnItem = new JButton("Select an Item...");
		btnSelectAnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ItemSelectionDialog dlg = new ItemSelectionDialog(team.getInventory(), ItemType.HEALING_ITEM);
				dlg.setVisible(true);
				
				if (dlg.getDialogResult() == DialogResult.OK) {
					
					selectedItem = (HealingItem)dlg.getSelectedItem();
					
					update();
					
				}
				
			}
		});
		panel_4.add(btnSelectAnItem);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		btnApplyHealingItem = new JButton("Apply Healing Item");
		btnApplyHealingItem.setEnabled(false);
		panel_5.add(btnApplyHealingItem);
		btnApplyHealingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectedHero.isHealing()) {
					
					JOptionPane.showMessageDialog(null, String.format("%s is already healing!", selectedHero.getName()));
					return;
					
				}
				
				selectedHero.useHealingItem(selectedItem);
				team.getInventory().remove(selectedItem);
				
				JOptionPane.showMessageDialog(null, String.format("%s is being healed with %s!", selectedHero.getName(), selectedItem.getName()));
				
				selectedHero = null;
				selectedItem = null;
				update();
				
			}
		});
		
		update();
		
	}
	
	public void update() {
		
		repaint();
		
		if (selectedItem == null) {
			
			lblSelectedItem.setText("None");
			
		} else {
			
			lblSelectedItem.setText(selectedItem.getName());
			
		}
		
		if (selectedHero == null) {
			
			lblSelectedHero.setText("None");
			
		} else {
			
			lblSelectedHero.setText(selectedHero.getName());
			
		}
		
		btnApplyHealingItem.setEnabled(selectedHero != null && selectedItem != null);
		
	}
}
