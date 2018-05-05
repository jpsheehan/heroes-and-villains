package game.ui.gui.dialogs;

import game.character.Hero;
import game.character.HeroType;

public class EditHeroDialog extends NewHeroDialog {

	
	private final String titleText = "Edit Hero";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1359879411147433759L;

	public EditHeroDialog(Hero hero) {
		
		super();
		this.setTitle(titleText);
		this.setHeroName(hero.getName());
		this.setHeroType(hero.getType());
		
	}
	
	public EditHeroDialog(String name, HeroType type) {
		
		super();
		this.setTitle(titleText);
		this.setHeroName(name);
		this.setHeroType(type);
		
	}
	
}
