package game;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import game.character.Hero;
import game.item.Item;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Contains static methods dealing with random state, extracting values from the "strings.json" file and other common methods not covered by the standard library.
 * @author jesse
 *
 */
public final class GeneralHelpers {
	
	// ######## IMAGES ########
	
	/**
	 * The image manager used to store all data in the images directory.
	 */
	public static ImageManager imageManager = new ImageManager();
	
	/**
	 * Returns an image from the images directory or null if an error occurred.
	 * @param filename The filename of the image within the "images" directory.
	 * @return
	 */
	public static BufferedImage getImage(String filename) {
		
		try {
			
			return ImageIO.read(GeneralHelpers.class.getClassLoader().getResourceAsStream("images/" + filename));
			
		} catch (Exception e) {
			
			return null;
			
		}
	    
	}
	
	// ######## STRINGS.JSON ########
	
	/**
	 * The object to load the contents of the "strings.json" file into.
	 */
	private static Map<String, String> strings = null;
	
	/**
	 * Gets the associated string from the strings.json file.
	 * @param specifier The string to get.
	 * @returns
	 */
	public static String getString(String specifier) {
		
		loadStrings();
		
		if (GeneralHelpers.strings.containsKey(specifier)) {
			
			String value = GeneralHelpers.strings.get(specifier);
			
			if (value.equals("")) {
				
				// return the specifier if value is empty.
				return String.format("[%s]", specifier);
				
			} else {
				
				// return the value if it exists.
				return value;
				
			}
			
		}
		
		throw new IllegalArgumentException(String.format("Invalid specifier \"%s\".", specifier));
		
	}
	
	/**
	 * Loads the strings from the strings.json file.
	 */
	private static void loadStrings() {
		
		if (GeneralHelpers.strings == null) {
			
			// Read the strings.json file into a String
			InputStream stream = GeneralHelpers.class.getClassLoader().getResourceAsStream("strings.json");
			Scanner scanner = new Scanner(stream);
			scanner.useDelimiter("\\A");
			
			String fileString = scanner.hasNext() ? scanner.next() : "";
			
			scanner.close();
			
			// Convert that string into a Map<String, String>
			Gson gson = new Gson();
			GeneralHelpers.strings = gson.fromJson(fileString, new TypeToken<Map<String, String>>(){}.getType());

		}
		
	}
	
	/**
	 * Gets the associated integer from the strings.json file.
	 * @param specifier The integer to get.
	 * @return
	 */
	public static int getInt(String specifier) {
		
		try {
			
			return (int)Integer.parseInt(getString(specifier));
			
		} catch (NumberFormatException e) {
			
			throw new IllegalArgumentException(String.format("Invalid integer for specifier \"%s\".", specifier));
			
		}
		
	}
	
	/**
	 * Gets the associated floating point number from the strings.json file.
	 * @param specifier The float to get.
	 * @return
	 */
	public static float getFloat(String specifier) {
		
		try {
			
			return (float)Float.parseFloat(getString(specifier));
			
		} catch (NumberFormatException e) {
			
			throw new IllegalArgumentException(String.format("Invalid float for specifier \"%s\".", specifier));
			
		}
		
	}
	
	/**
	 * @return An array containing all the items in the strings.json file.
	 */
	public static Item[] getItemPool() {
		
		ArrayList<String> itemNames = new ArrayList<String>();
		
		for (String key : strings.keySet()) {
			
			if (key.startsWith("Item.")) {
				
				try {
					String itemName = key.split("\\.")[1];
					
					if (!itemNames.contains(key)) {
						itemNames.add(itemName);
					}
					
				} catch (IndexOutOfBoundsException e) {}
				
			}
			
		}
		
		Item[] items = new Item[itemNames.size()];
		for (int i = 0; i < itemNames.size(); i++) {
			items[i] = Item.fromStrings(itemNames.get(i), 1f);
		}
		
		return items;
		
	}

	// ######## RANDOM STATE METHODS ########
	
	/**
	 * The random state used for the game.
	 */
	private static Random randomState = null;
	
	/**
	 * Seeds the random state with a particular number (Useful for testing)
	 * @param seed The seed to use.
	 */
	public static void seedRandom(long seed) {
		
		GeneralHelpers.randomState = new Random();
		randomState.setSeed(seed);
		
	}
	
	/**
	 * Gets the random state for the game.
	 * @return
	 */
	public static Random getRandom() {
		
		if (GeneralHelpers.randomState == null) {
			
			GeneralHelpers.randomState = new Random();
			
		}
		
		return GeneralHelpers.randomState;
		
	}
	
	/**
	 * Gets the random number seed value.
	 * @return
	 */
	public static void setRandom(Random random) {
		
		GeneralHelpers.randomState = random;;
		
	}
	
	// ######## MISC. ########
	
	/**
	 * Shuffles an ArrayList (poorly).
	 * @param list The list to shuffle.
	 * @return Returns a copy of the list shuffled.
	 */
	public static <T> ArrayList<T> shuffleArrayList(ArrayList<T> list) {
		
		@SuppressWarnings("unchecked")
		ArrayList<T> copy = (ArrayList<T>)list.clone();
		
		ArrayList<T> newList = new ArrayList<T>();
		
		while (!copy.isEmpty()) {
			
			newList.add(copy.remove(GeneralHelpers.getRandom().nextInt(copy.size())));
			
		}
		
		return newList;
		
	}
	
	/**
	 * Checks if the array contains a particular item.
	 * @param array The array to iterate over.
	 * @param item The item that is being sought.
	 * @return True if the item is found, false otherwise.
	 */
	public static <T> boolean doesArrayContain(T[] array, T item) {
		
		for (T elem : array) {
			
			if (elem == item || elem.equals(item)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Returns the largest of the doubles. 
	 * @param args Any number of doubles.
	 * @return The largest double.
	 */
	public static double max(double ...args) {
		
		double value = Double.MIN_VALUE;
		
		for (double v : args) {
			
			if (v > value) {
				
				value = v;
				
			}
			
		}
		
		return value;
		
	}
	
	/**
	 * Returns the smallest of the doubles.
	 * @param args Any number of doubles.
	 * @return The smallest double.
	 */
	public static double min(double ...args) {
		
		double value = Double.MAX_VALUE;
		
		for (double v : args) {
			
			if (v < value) {
				
				value = v;
				
			}
			
		}
		
		return value;
		
	}
	
	public static float getGPA(GameEnvironment env) {
		
		int numberOfCities = (env.getCityController().getCityIndex() + 1);
		
		float heroHealthPercentage = 0f;
		for (Hero hero : env.getTeam().getHeroes()) {
			if (hero.isAlive()) {
				heroHealthPercentage += (float) (hero.getHealth() / hero.getMaxHealth());
			}
		}
		
		return (float) max(9, 9 * (numberOfCities / 6f) * (heroHealthPercentage / (float) env.getTeam().getHeroes().length) * (env.getNumberOfSeconds() / 120f * numberOfCities));
		
	}
	
//	public String getGradeLetter(float gpa) {
//	
//		// if (gpa < )
//		
//	}
}
