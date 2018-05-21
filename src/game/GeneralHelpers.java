package game;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

public final class GeneralHelpers {
	
	/**
	 * The random seed to use.
	 */
	private static long randomSeed;
	
	/**
	 * The number of times random.nextInt has been called.
	 */
	private static int randomIterations;
	
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
	
	public static ImageManager imageManager = new ImageManager();
	
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
	
	public static BufferedImage getImage(String filename) {
		
		try {
			
			return ImageIO.read(GeneralHelpers.class.getClassLoader().getResourceAsStream("images/" + filename));
			
		} catch (Exception e) {
			
			return null;
			
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
	
	private static boolean runningInEclipse;
	
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
	
	private static Map<String, String> strings = null;
	
	public static boolean isRunningInEclipse() {
		
		return ("true".equalsIgnoreCase(System.getProperty("runningInEclipse")) || runningInEclipse);
		
	}
	
	public static void setIsRunningInEclipse(boolean value) {
		runningInEclipse = value;
	}

	/**
	 * The random state used for the game.
	 */
	private static Random randomState = null;
	
	public static void seedRandom() {
		
		seedRandom((new Date()).getTime(), 0);
		
	}
	
	/**
	 * Seeds the random state with a particular number (Useful for testing)
	 * @param seed The seed to use.
	 */
	public static void seedRandom(long seed, int iterations) {
		
		GeneralHelpers.randomSeed = seed;
		GeneralHelpers.randomIterations = iterations;
		GeneralHelpers.randomState = new Random();
		GeneralHelpers.randomState.setSeed(seed);
		
		for (int i = 0; i < GeneralHelpers.randomIterations; i++) {
			
			GeneralHelpers.getRandomNextInt();
			
		}
		
	}
	
	/**
	 * Gets the random state for the game.
	 * @return
	 */
	public static int getRandomNextInt() {
		
		if (GeneralHelpers.randomState == null) {
			
			seedRandom();
			
		}
		
		GeneralHelpers.randomIterations++;
		
		return GeneralHelpers.randomState.nextInt();
		
	}
	
	public static int getRandomNextInt(int bounds) {
		
		if (GeneralHelpers.randomState == null) {
			
			seedRandom();
			
		}

		GeneralHelpers.randomIterations++;
		
		return GeneralHelpers.randomState.nextInt(bounds);
		
	}
	
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
			
			newList.add(copy.remove(GeneralHelpers.getRandomNextInt(copy.size())));
			
		}
		
		return newList;
		
	}
	
	public static <T> boolean doesArrayContain(T[] array, T item) {
		
		for (T elem : array) {
			
			if (elem == item || elem.equals(item)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public static double max(double ...args) {
		
		double value = Double.MIN_VALUE;
		
		for (double v : args) {
			
			if (v > value) {
				
				value = v;
				
			}
			
		}
		
		return value;
		
	}
	
	public static double min(double ...args) {
		
		double value = Double.MAX_VALUE;
		
		for (double v : args) {
			
			if (v < value) {
				
				value = v;
				
			}
			
		}
		
		return value;
		
	}
	
	/**
	 * Gets the random number seed value.
	 * @return
	 */
	public static long getRandomSeed() {
		
		return GeneralHelpers.randomSeed;
		
	}
	
	public static int getRandomIterations() {
		
		return GeneralHelpers.randomIterations;
		
	}
}
