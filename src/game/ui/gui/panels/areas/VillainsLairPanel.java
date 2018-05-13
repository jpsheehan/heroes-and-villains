package game.ui.gui.panels.areas;

import javax.swing.JPanel;

import game.ui.gui.Triggerable;

public class VillainsLairPanel extends GenericAreaPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -921176693627897514L;

	private Triggerable window;
	
	/**
	 * Create the panel.
	 */
	public VillainsLairPanel(Triggerable window) {

		this.window = window;
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		repaint();
	}

}
