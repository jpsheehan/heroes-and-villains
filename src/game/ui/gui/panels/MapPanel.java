package game.ui.gui.panels;

import javax.swing.JPanel;

import game.city.CityController;
import game.city.Direction;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Component;
import javax.swing.Box;

public class MapPanel extends JPanel {
	
	public Color unvisitedColor = Color.GRAY;
	public Color visitedColor = Color.WHITE;
	public Color errorColor = Color.RED;
	public Color occupiedColor = Color.GREEN;
	
	private CityController cityController;

	/**
	 * Required for implementing the Serializable interface.
	 */
	private static final long serialVersionUID = -986779385167529652L;

	/**
	 * Create the panel.
	 * @param cityController
	 */
	public MapPanel(CityController cityController) {
		setBackground(Color.BLACK);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				
				setSize(128, 128);
				
			}
		});
		
		this.cityController = cityController;
		
		Component rigidArea = Box.createRigidArea(new Dimension(128, 128));
		add(rigidArea);
		
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
			
			for (Direction direction : Direction.values()) {
				
				if (cityController.hasVisitedDirection(direction)) {
					
					Point position = this.getAreaPosition(direction, width, height);
	
					// fill the area with a solid color.
					g.setColor(visitedColor);
					g.fillRect(position.x, position.y, width / 3, height / 3);
					
					// If this area is occupied by the team, draw a green circle
					if (cityController.getDirection() == direction) {
						
						boolean wider = (width / 3 > height / 3);
						
						int diameter = (wider ? height / 3 : width / 3) - 16;
						int x = (width / 3 - diameter) / 2 + position.x;
						int y = (height / 3 - diameter) / 2 + position.y;
						
						g.setColor(occupiedColor);
						g.fillOval(x, y, diameter, diameter);
						
					}
					
					// Draw the area legend
					g.setColor(getForeground());
					String legend = cityController.getCurrentCity().getArea(direction).getType().getMapLegend();
					g.drawString(legend, position.x + (width / 3 - metrics.stringWidth(legend)) / 2, position.y + (height / 3 + metrics.getHeight() / 2) / 2);
					
				}
				
			}
			
		}
		
	}
	
	private Point getAreaPosition(Direction direction, int width, int height) {
		
		switch (direction) {
		
			case CENTRE:
				return new Point(width / 3, height / 3);
				
			case EAST:
				return new Point(2 * width / 3, height / 3);
				
			case NORTH:
				return new Point(width / 3, 0);
				
			case SOUTH:
				return new Point(width / 3, 2 * height / 3);
				
			case WEST:
				return new Point(0, height / 3);
			
			default:
				throw new AssertionError();
		
		}
		
	}
	
}
