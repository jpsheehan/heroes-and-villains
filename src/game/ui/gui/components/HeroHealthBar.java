package game.ui.gui.components;

import game.character.Hero;

public class HeroHealthBar extends HealthBar {

	private Hero hero;
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -7004751552534381921L;
	
	public HeroHealthBar(Hero hero) {
		
		super(200);
		
		this.hero = hero;
		
		update();
	}
	
	@Override
	public void update() {

		if (hero != null) {
			
			this.setCurrentValue(hero.getHealth());
			this.setMaxValue(hero.getMaxHealth());
			
		}
		
		super.update();
		
	}
	
	public void setHero(Hero hero) {
		
		this.hero = hero;
		
		update();
		
	}

}
