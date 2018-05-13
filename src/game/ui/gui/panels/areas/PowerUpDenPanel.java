package game.ui.gui.panels.areas;

import javax.swing.JPanel;

import game.ui.gui.Triggerable;

public class PowerUpDenPanel extends GenericAreaPanel {

	private Triggerable window;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6790779228677501246L;

	/**
	 * Create the panel.
	 */
	public PowerUpDenPanel(Triggerable window) {
		
		this.window = window;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		repaint();
	}

}
