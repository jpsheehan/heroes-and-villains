package game.ui.gui.panels;

import javax.swing.JPanel;

import game.ui.gui.Triggerable;

public class HomeBasePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7383141118407290522L;
	
	private Triggerable window;

	/**
	 * Create the panel.
	 */
	public HomeBasePanel(Triggerable window) {
		
		this.window = window;
		
	}

}
