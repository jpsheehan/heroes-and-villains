package game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;

public class ImageManager {
	
	private HashMap<String, Image> images;
	private boolean loaded;
	private String lastLoaded;
	private int imageCount;
	private int loadedCount;

	public ImageManager() {
		
		images = new HashMap<String, Image>();
		loaded = false;
		lastLoaded = null;
		imageCount = 0;
		
	}
	
	public void loadAllImages() {
		
		InputStream inStream = ImageManager.class.getClassLoader().getResourceAsStream("images");
		BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
		
		// we need to do this kind of awkwardly so that we can see the number of images in advance of actually reading them
		Stream<String> lines = buffer.lines();
		Object[] objs = lines.toArray();
		
		imageCount = objs.length;
		
		for (Object obj : objs) {
			
			String filename = (String)obj;

			Image img = GeneralHelpers.getImage(filename);
			
			images.put(filename, img);
			lastLoaded = filename;
			loadedCount++;
			
		}
		
		loaded = true;
		
	}
	
	public Image get(String name) {
		
		return this.images.get(name);
		
	}
	
	public boolean getLoaded() {
		
		return this.loaded;
		
	}
	
	public String getLastLoaded() {
		
		return this.lastLoaded;
		
	}
	
	public int size() {
		
		if (loaded) {
			
			return images.size();
			
		}
		
		return imageCount;
		
	}
	
	public int getNumberOfLoadedImages() {
		
		return loadedCount;
		
	}
	
}
