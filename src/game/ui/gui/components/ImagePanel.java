package game.ui.gui.components;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

import game.GeneralHelpers;
import javax.swing.BoxLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ImagePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2127501600065258904L;
	private Image image;
	private Image scaledImage;
	public String path;
	
	private int xOffset = 0, yOffset = 0;
	
	public ImagePanel() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				
				scaledImage = null;
				repaint();
				
			}
		});
		
		this.path = null;
		this.image = null;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
	}
	
	public ImagePanel(String path) {

		this.setImagePath(path);
		this.loadImage();
		
	}
	
	public void setImagePath(String path) {
		
		this.path = path;
		this.loadImage();
		
	}
	
	private void loadImage() {
		
		this.image = GeneralHelpers.imageManager.get(this.path);
		this.scaledImage = null;
		repaint();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (this.scaledImage == null && this.image != null && getHeight() > 0 && getWidth() > 0) {
			
			// DUMB SCALING
			// this.scaledImage = this.image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			
			// SMART SCALING			
			float ratio = (float)getHeight() / (float)image.getHeight(null);
			int newWidth = (int)(ratio * (float)image.getWidth(null));
			int newHeight = (int)(ratio * (float)image.getHeight(null));
			
			this.scaledImage = this.image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			xOffset = (int)(((float)getWidth() - (float)scaledImage.getWidth(null)) / 2f);
			yOffset = (int)(((float)getHeight() - (float)scaledImage.getHeight(null)) / 2f);
			
		}
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (this.scaledImage != null) {
			
			g.drawImage(this.scaledImage, xOffset, yOffset, this);
			
		}
		
	}

}
