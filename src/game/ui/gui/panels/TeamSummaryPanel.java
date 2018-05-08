package game.ui.gui.panels;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class TeamSummaryPanel extends JPanel {
	
	private Team team;
	private HeroSummaryPanel summaries[];

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
		
		if (this.team != null) {
			
			lblTeamName.setText(team.getName());
			
			this.summaries = new HeroSummaryPanel[team.getHeroes().size()];
			
			int i = 0;
			for (Hero hero : team.getHeroes()) {
				
				this.summaries[i] = new HeroSummaryPanel(hero);
				heroesPanel.add(this.summaries[i++]);
				
			}
			
		}
		
	}
	
	public void update() {
		
		for (HeroSummaryPanel summary : this.summaries) {
			
			summary.update();
			
		}
		
	}

}
