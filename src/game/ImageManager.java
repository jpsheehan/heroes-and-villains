package game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Manages the loading and caching of images from disk (who would have guessed?!)
 * @author jesse
 *
 */
public class ImageManager {
	
	/**
	 * The mapping of strings to Image objects internally.
	 */
	private HashMap<String, Image> images;
	
	/**
	 * Set to true if all the images have been loaded.
	 */
	private boolean loaded;
	
	/**
	 * The key of the latest Image to be loaded.
	 */
	private String lastLoaded;
	
	/**
	 * The number of total images to load.
	 */
	private int imageCount;
	
	/**
	 * The number of images currently loaded.
	 */
	private int loadedCount;

	/**
	 * Creates a new ImageManager instance.
	 */
	public ImageManager() {
		
		images = new HashMap<String, Image>();
		loaded = false;
		lastLoaded = null;
		imageCount = 0;
		
	}
	
	/**
	 * Loads all images from the images subdirectory.
	 */
	public void loadAllImages() {
		
		InputStream inStream = ImageManager.class.getClassLoader().getResourceAsStream(Settings.getImagesDirectory());
		BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
		
		// we need to do this kind of awkwardly so that we can see the number of images in advance of actually reading them
		Stream<String> lines = buffer.lines();
		Object[] objs = lines.toArray();
		
		// set the number of images we are looking to load
		imageCount = objs.length;
		
		// begin loading images.
		for (Object obj : objs) {
			
			String filename = (String)obj;

			Image img = GeneralHelpers.getImage(filename);
			
			images.put(filename, img);
			lastLoaded = filename;
			loadedCount++;
			
		}
		
		loaded = true;
		
	}
	
	/**
	 * Gets an image with the particular key.
	 * @param key The key that corresponds to a particular Image.
	 * @return The corresponding Image.
	 */
	public Image get(String key) {
		
		return this.images.get(key);
		
	}
	
	/**
	 * @return True if all images have been loaded. False otherwise.
	 */
	public boolean getLoaded() {
		
		return this.loaded;
		
	}
	
	/**
	 * @return A string containing the key of the last image to be loaded.
	 */
	public String getLastLoaded() {
		
		return this.lastLoaded;
		
	}
	
	/**
	 * The number of images that have been loaded or (hopefully) will be loaded.
	 * @return The number of images.
	 */
	public int size() {
		
		if (loaded) {
			
			return images.size();
			
		}
		
		return imageCount;
		
	}
	
	/**
	 * @return The number of images loaded so far.
	 */
	public int getNumberOfLoadedImages() {
		
		return loadedCount;
		
	}
	
}
