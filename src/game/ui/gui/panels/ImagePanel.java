package game.ui.gui.panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2127501600065258904L;
	private BufferedImage image;
	private String path;
	
	public ImagePanel() {
		
		this.path = null;
		this.image = null;
		
	}
	
	public ImagePanel(String path) {
		
		this.loadImage();
		this.setImagePath(path);
		
	}
	
	public void setImagePath(String path) {
		
		this.path = path;
		this.loadImage();
		
	}
	
	private void loadImage() {
		
		try {
			
			this.image = ImageIO.read(new File(this.path));
			
		} catch (IOException e) {
			
			this.image = null;
			
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (this.image != null) {
			
			g.drawImage(this.image, 0, 0, this);
			
		}
		
	}

}
