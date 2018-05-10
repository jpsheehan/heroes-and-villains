package game.ui.gui.panels;

import javax.swing.JPanel;

import game.city.CityController;

import javax.swing.JLabel;
import javax.swing.BoxLayout;

public class AreaSummaryPanel extends JPanel {
	
	private CityController cityController;
	private JLabel lblCityName, lblAreaName, lblAreaType, lblCityFlavour, lblAreaFlavour;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7028985661468975792L;

	/**
	 * Create the panel.
	 */
	public AreaSummaryPanel(CityController cityController) {
	
		this.cityController = cityController;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		
		lblCityName = new JLabel("<lblCityName>");
		panel.add(lblCityName);
		
		JLabel lblNewLabel = new JLabel(">");
		panel.add(lblNewLabel);
		
		lblAreaName = new JLabel("<lblAreaName>");
		panel.add(lblAreaName);
		
		lblAreaType = new JLabel("<lblAreaType>");
		panel.add(lblAreaType);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		
		lblCityFlavour = new JLabel("<lblCityFlavour>");
		panel_1.add(lblCityFlavour);
		
		JPanel panel_2 = new JPanel();
		add(panel_2);
		
		lblAreaFlavour = new JLabel("<lblAreaFlavour>");
		panel_2.add(lblAreaFlavour);
		
		update();

	}
	
	public void update() {
		
		if (this.cityController == null) {
			
			lblCityName.setText("NULL");
			lblCityFlavour.setText("NULL");
			lblAreaName.setText("NULL");
			lblAreaType.setText("(NULL)");
			lblAreaFlavour.setText("NULL");
			
		} else {

			lblCityName.setText(cityController.getCurrentCity().getName());
			lblCityFlavour.setText(cityController.getCurrentCity().getFlavourText());
			lblAreaName.setText(cityController.getCurrentArea().getName());
			lblAreaType.setText("(" + cityController.getCurrentArea().getType().toString() + ")");
			lblAreaFlavour.setText(cityController.getCurrentArea().getFlavourText());
			
		}
		
	}
}
