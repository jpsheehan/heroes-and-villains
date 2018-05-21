package game.ui.gui.panels.areas;

import javax.swing.JPanel;

public abstract class GenericAreaPanel extends JPanel {
	
	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -1458093874814031984L;

	public abstract void update();

}
