package game.ui.gui.panels;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

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
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		namePanel.add(horizontalStrut);
		
		JLabel lblFunds = new JLabel("Funds:");
		namePanel.add(lblFunds);
		
		lblMoney = new JLabel("<lblMoney>");
		namePanel.add(lblMoney);
		
		JPanel heroesPanel = new JPanel();
		add(heroesPanel);
		heroesPanel.setLayout(new BoxLayout(heroesPanel, BoxLayout.Y_AXIS));
		
		if (this.team != null) {
			
			lblTeamName.setText(team.getName());
			
			this.summaries = new HeroSummaryPanel[team.getHeroes().size()];
			
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
