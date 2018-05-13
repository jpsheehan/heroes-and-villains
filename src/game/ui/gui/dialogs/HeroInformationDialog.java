package game.ui.gui.dialogs;

import javax.swing.JDialog;

import game.character.Hero;
import game.character.HeroType;

public class HeroInformationDialog extends HeroSelectionDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8771057294615620600L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HeroInformationDialog dialog = new HeroInformationDialog(new Hero("Alice", HeroType.ARTS_STUDENT));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HeroInformationDialog(Hero hero) {
		
		super(new Hero[] { hero });
		makeReadOnly();
		setTitle("Hero Information");
		
	}

}
