package game.ui.gui.panels;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import game.ui.gui.dialogs.ItemSelectionDialog;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamSummaryPanel extends JPanel {
	
	private Team team;
	private HeroSummaryPanel summaries[];
	private JLabel lblMoney;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6866876273299198271L;

	/**
	 * Create the panel.
	 */
	public TeamSummaryPanel(Team team) {
		
		this.team = team;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		add(namePanel);
		
		JLabel lblTeamNameDescription = new JLabel("Team Name:");
		namePanel.add(lblTeamNameDescription);
		
		JLabel lblTeamName = new JLabel("<lblTeamName>");
		namePanel.add(lblTeamName);
		
		JPanel heroesPanel = new JPanel();
		add(heroesPanel);
		heroesPanel.setLayout(new BoxLayout(heroesPanel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut);
		
		JPanel inventoryPanel = new JPanel();
		add(inventoryPanel);
		inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.X_AXIS));
		
		JLabel lblFunds = new JLabel("Funds: ");
		inventoryPanel.add(lblFunds);
		
		lblMoney = new JLabel("<lblMoney>");
		inventoryPanel.add(lblMoney);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		inventoryPanel.add(horizontalStrut);
		
		JButton btnInventory = new JButton("Inventory...");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new ItemSelectionDialog(team.getInventory(), null, false, false)).setVisible(true);
			}
		});
		inventoryPanel.add(btnInventory);
		
		if (this.team != null) {
			
			lblTeamName.setText(team.getName());
			
			this.summaries = new HeroSummaryPanel[team.getHeroes().length];
			
			int i = 0;
			for (Hero hero : team.getHeroes()) {
				
				this.summaries[i] = new HeroSummaryPanel(hero);
				heroesPanel.add(this.summaries[i++]);
				
			}
			
		}
		
		update();
		
	}
	
	public void update() {
		
		if (this.team != null) {
		
			for (HeroSummaryPanel summary : this.summaries) {
				
				summary.update();
				
			}
			
			lblMoney.setText(String.format("$%d", this.team.getMoney()));
		
		}
		
	}

}
