package game.ui.gui.dialogs;

import game.character.Hero;

public class HeroInformationDialog extends HeroSelectionDialog {

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = 8771057294615620600L;

	/**
	 * Create the dialog.
	 * @param hero The Hero whose information will be displayed
	 */
	public HeroInformationDialog(Hero hero) {
		
		super(new Hero[] { hero });
		makeReadOnly();
		setTitle("Hero Information");
		
	}

}
