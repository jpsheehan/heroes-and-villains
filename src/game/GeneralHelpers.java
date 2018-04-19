package game;

import java.io.InputStream;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public final class GeneralHelpers {
	
	/**
	 * Gets the associated string from the strings.json file.
	 * @param specifier The string to get.
	 * @returns
	 */
	public static String getString(String specifier) {
		
		loadStrings();
		
		if (GeneralHelpers.strings.containsKey(specifier)) {
			
			return GeneralHelpers.strings.get(specifier);
			
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
	
	private static Map<String, String> strings = null;
	
	/**
	 * A wrapper for getString. Returns the associated string.
	 * @param specifier The specifier that is associated with the string you want.
	 * @return
	 */
	public static String $(String specifier) {
		
		return getString(specifier);
		
	}
	
	public static boolean isRunningInEclipse() {
		
		return ("true".equalsIgnoreCase(System.getProperty("runningInEclipse")));
		
	}

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
	
	public static <T> boolean doesArrayContain(T[] array, T item) {
		
		for (T elem : array) {
			
			if (elem == item || elem.equals(item)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
}
