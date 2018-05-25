package game.ui.gui.panels.areas;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Team;
import game.character.Hero;
import game.ui.gui.DialogResult;
import game.ui.gui.GameEventListener;
import game.ui.gui.dialogs.HeroSelectionDialog;
import game.ui.gui.dialogs.ItemSelectionDialog;
import game.ui.gui.windows.GameWindow;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import game.item.HealingItem;
import game.item.ItemType;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * GUI for the Hospital area
 * @author jesse
 *
 */
public class HospitalPanel extends GenericAreaPanel implements ActionListener {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -6330847966216959076L;
	
	private Hero selectedHero;
	private HealingItem selectedItem;
	
	private JButton btnApplyHealingItem;
	private JLabel lblSelectedItem, lblSelectedHero, lblAreHeroesBeingHealed;
	private JPanel panelHeroHealingParent;
	private Team team;
	
	private Map<Hero, JLabel> healingLabels;

	/**
	 * Create the panel.
	 * @param window The parent event listener.
	 * @param team The team that is playing the game.
	 */
	public HospitalPanel(GameEventListener window, Team team) {
		
		this.healingLabels = new HashMap<Hero, JLabel>();
		this.team = team;

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
				dlg.setLocationRelativeTo(GameWindow.getMainWindow());
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
				
				if (team.getInventory().getHealingItems().length == 0) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), "You have no healing items to choose from.", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					
					ItemSelectionDialog dlg = new ItemSelectionDialog(team.getInventory(), ItemType.HEALING_ITEM, false, true);
					dlg.setLocationRelativeTo(GameWindow.getMainWindow());
					dlg.setVisible(true);
					
					if (dlg.getDialogResult() == DialogResult.OK) {
						
						selectedItem = (HealingItem)dlg.getSelectedItem();
						
						update();
						
					}
					
				}
				
			}
		});
		panel_4.add(btnSelectAnItem);
		
		JPanel panel_5 = new JPanel();
		panel_6.add(panel_5);
		
		btnApplyHealingItem = new JButton("Apply Healing Item");
		btnApplyHealingItem.setEnabled(false);
		panel_5.add(btnApplyHealingItem);
		
		JPanel panel_7 = new JPanel();
		panel_6.add(panel_7);
		
		lblAreHeroesBeingHealed = new JLabel("The following heroes are currently being healed:");
		lblAreHeroesBeingHealed.setVisible(false);
		panel_7.add(lblAreHeroesBeingHealed);
		
		panelHeroHealingParent = new JPanel();
		panel_6.add(panelHeroHealingParent);
		panelHeroHealingParent.setLayout(new BoxLayout(panelHeroHealingParent, BoxLayout.Y_AXIS));
		btnApplyHealingItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (selectedHero.isHealing()) {
					
					JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("%s is already healing!", selectedHero.getName()), "Error", JOptionPane.ERROR_MESSAGE);
					return;
					
				}
				
				selectedHero.useHealingItem(selectedItem);
				team.getInventory().remove(selectedItem);
				
				JOptionPane.showMessageDialog(GameWindow.getMainWindow(), String.format("%s is being healed with %s!", selectedHero.getName(), selectedItem.getName()), "Success", JOptionPane.INFORMATION_MESSAGE);
				
				selectedHero = null;
				selectedItem = null;
				update();
				
			}
		});
		
		update();
		
		Timer timer = new Timer(500, this);
		GameWindow.addTimer(timer);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean areHeroesBeingHealed = false;
		
		for (Hero hero : team.getHeroes()) {
			
			String healingString = String.format("%s (%ds remaining)", hero.getName(), hero.getRemainingHealingTime());
			
			if (hero.isHealing() && !healingLabels.containsKey(hero)) {
				
				JPanel newPanel = new JPanel();
				JLabel newLabel = new JLabel();
				newLabel.setText(healingString);
				
				newPanel.add(newLabel);
				panelHeroHealingParent.add(newPanel);
				
				healingLabels.put(hero,  newLabel);
				
			}
			
			if (healingLabels.containsKey(hero)) {
				
				if (hero.isHealing()) {
					
					healingLabels.get(hero).setText(healingString);
					areHeroesBeingHealed = true;
					
				} else {
					
					panelHeroHealingParent.remove(healingLabels.get(hero).getParent());
					healingLabels.remove(hero);
					
				}
				
			}
			
		}
		
		lblAreHeroesBeingHealed.setVisible(areHeroesBeingHealed);
		
	}
	
}
