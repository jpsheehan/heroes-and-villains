package game;

import java.util.ArrayList;
import java.util.Random;

public final class GeneralHelpers {
	
	public static String getString(String specifier) {
		
		throw new IllegalArgumentException(String.format("Invalid specifier \"%s\".", specifier));
		
	}
	
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
