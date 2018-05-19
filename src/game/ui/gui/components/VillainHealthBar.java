package game.ui.gui.components;

import game.character.Villain;

public class VillainHealthBar extends HealthBar {

	private Villain villain;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1686860956476265548L;

	public VillainHealthBar(Villain villain) {
		
		super(200);
		this.villain = villain;
		
		update();
		
	}
	
	public void update() {
		
		if (villain != null ) {
			
			setCurrentValue(villain.getNumberOfWinsToDefeat());
			setMaxValue(villain.getNumberOfWinsToDefeat());
			
		}
		
		super.update();
		
	}

}
