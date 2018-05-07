package game.ui.gui.panels;

import javax.swing.JPanel;

import game.city.CityController;
import game.city.Direction;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class MapPanel extends JPanel {
	
	public Color unvisitedColor = Color.GRAY;
	public Color visitedColor = Color.WHITE;
	public Color errorColor = Color.RED;
	
	private CityController cityController;

	/**
	 * 
	 */
	private static final long serialVersionUID = -986779385167529652L;

	/**
	 * Create the panel.
	 */
	public MapPanel(CityController cityController) {
		setLayout(null);
		
		this.cityController = cityController;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		int width = getWidth(), height = getHeight();
		
		// set the font
		g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, height / 9));
		FontMetrics metrics = g.getFontMetrics();
		
		// Clear the panel
		g.setColor(unvisitedColor);
		g.fillRect(0, 0, width, height);
		
		if (this.cityController == null) {

			// if cityController is null, draw an error message in the centre of the MapPanel
			String message = "NULL";
			int messageWidth = metrics.stringWidth(message);
			int messageHeight = metrics.getHeight();
			
			g.setColor(errorColor);
			g.drawString(message, (width - messageWidth) / 2, (height - messageHeight) / 2);
			
		} else {
			
			// Fill certain areas in black (they can't be reached)
			g.setColor(getBackground());
			g.fillRect(0, 0, width / 3, height / 3);
			g.fillRect(2 * width / 3, 0, width / 3, height / 3);
			g.fillRect(0, 2 * height / 3, width / 3,  height / 3);
			g.fillRect(2 * width / 3, 2 * height / 3, width / 3, height / 3);
			
			// Fill the visited areas in
			if (cityController.hasVisitedDirection(Direction.CENTRE)) {

				g.setColor(visitedColor);
				g.fillRect(width / 3, height / 3, width / 3, height / 3);
				
				g.setColor(getForeground());
				String legend = cityController.getCurrentCity().getArea(Direction.CENTRE).getType().getMapLegend();
				g.drawString(legend, (width - metrics.stringWidth(legend)) / 2, (height + metrics.getHeight() / 2) / 2);
				
			}
			
//			if (cityController.hasVisitedDirection(Direction.NORTH)) {
//
//				g.setColor(visitedColor);
//				g.fillRect(width / 3, 0, width / 3, height / 3);
//				
//				g.setColor(getForeground());
//				String legend = cityController.getCurrentCity().getArea(Direction.CENTRE).getType().getMapLegend();
//				g.drawString(legend, (width - g.getFontMetrics().stringWidth(legend)) / 2, height / 2);
//				
//			}
//			
//			if (cityController.hasVisitedDirection(Direction.WEST)) {
//
//				g.setColor(visitedColor);
//				g.fillRect(0, height / 3, width / 3, height / 3);
//				
//				g.setColor(getForeground());
//				String legend = cityController.getCurrentCity().getArea(Direction.CENTRE).getType().getMapLegend();
//				g.drawString(legend, (width - g.getFontMetrics().stringWidth(legend)) / 2, (height - g.getFontMetrics().getHeight()) / 2);
//				
//			}
//			
//			if (cityController.hasVisitedDirection(Direction.EAST)) {
//
//				g.setColor(visitedColor);
//				g.fillRect(2 * width / 3, height / 3, width / 3, height / 3);
//				
//				g.setColor(getForeground());
//				String legend = cityController.getCurrentCity().getArea(Direction.CENTRE).getType().getMapLegend();
//				g.drawString(legend, (width - g.getFontMetrics().stringWidth(legend)) / 2, (height - g.getFontMetrics().getHeight()) / 2);
//				
//			}
//			
//			if (cityController.hasVisitedDirection(Direction.SOUTH)) {
//
//				g.setColor(visitedColor);
//				g.fillRect(width / 3, 2 * height / 3, width / 3, height / 3);
//				
//				g.setColor(getForeground());
//				String legend = cityController.getCurrentCity().getArea(Direction.CENTRE).getType().getMapLegend();
//				g.drawString(legend, (width - g.getFontMetrics().stringWidth(legend)) / 2, (height - g.getFontMetrics().getHeight()) / 2);
//				
//			}
		}
	}
	
}
