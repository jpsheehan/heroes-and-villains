package game.ui.gui.panels;

import javax.swing.JPanel;

import game.Team;
import game.character.Hero;
import java.awt.FlowLayout;

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
		
		if (this.team != null) {
			
			this.summaries = new HeroSummaryPanel[team.getHeroes().size()];
			
			int i = 0;
			for (Hero hero : team.getHeroes()) {
				
				this.summaries[i++] = new HeroSummaryPanel(hero);
				add(this.summaries[i]);
				
			}
			
		}
		
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
	}
	
	public void update() {
		
		for (HeroSummaryPanel summary : this.summaries) {
			
			summary.update();
			
		}
		
	}

}
