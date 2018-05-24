package game;

import java.awt.Image;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
	 * Holds all of the filenames in the images directory.
	 */
	private String[] filenames;

	/**
	 * Creates a new ImageManager instance.
	 */
	public ImageManager() {
		
		images = new HashMap<String, Image>();
		loaded = false;
		lastLoaded = null;
		imageCount = 0;
		filenames = null;
		
	}
	
	//TODO
	/**
	 *
	 */
	private void readImagesDirectory() {
		
		// this method is from https://stackoverflow.com/questions/1429172/how-do-i-list-the-files-inside-a-jar-file
		try {
			URI uri = ImageManager.class.getResource(Settings.getImagesDirectory()).toURI();
			Path path;
			
			if (uri.getScheme().equals("jar")) {
				FileSystem fs = FileSystems.newFileSystem(uri, Collections.<String, Object>emptyMap());
				path = fs.getPath(Settings.getImagesDirectory());
			} else {
				path = Paths.get(uri);
			}
	
			ArrayList<String> lines = new ArrayList<String>();
			Stream<Path> walk = Files.walk(path, 1);
			for (Iterator<Path> it = walk.iterator(); it.hasNext();) {
				lines.add(it.next().getFileName().toString());
			}
			walk.close();
			
			// we need to do this kind of awkwardly so that we can see the number of images in advance of actually reading them
			Object[] objs = lines.toArray();
			
			// set the number of images we are looking to load
			imageCount = objs.length;
			
			filenames = new String[objs.length];
			
			for (int i = 0; i < filenames.length; i++) {
				
				filenames[i] = (String)objs[i];
				
			}
		
		} catch (Exception e) {
			
			System.out.println(e.getStackTrace());
			
		}
	}
	
	/**
	 * Loads all images from the images subdirectory.
	 */
	public void loadNextImage() {
		
		if (filenames == null) {
			
			readImagesDirectory();
			
		}
		
		if (loaded) {
			return;
		}
		
		try {
			String filename = filenames[loadedCount++];
	
			Image img = GeneralHelpers.getImage(filename);
			
			images.put(filename, img);
			lastLoaded = filename;
			
		} catch (IndexOutOfBoundsException e) {
			
			loaded = true;
			throw e;
			
		}
		
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
	 * @param key The key to search for.
	 * @return True if the image with the key exists, false otherwise.
	 */
	public boolean contains(String key) {
		
		return this.images.containsKey(key);
		
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
