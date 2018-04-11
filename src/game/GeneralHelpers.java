package game;

import java.util.ArrayList;
import java.util.Random;

public final class GeneralHelpers {

	/**
	 * Shuffles an ArrayList (poorly).
	 * @param list The list to shuffle.
	 * @return Returns a copy of the list shuffled.
	 */
	public static <T> ArrayList<T> shuffleArrayList(ArrayList<T> list) {
		
		@SuppressWarnings("unchecked")
		ArrayList<T> copy = (ArrayList<T>)list.clone();
		
		Random rand = new Random();
		
		ArrayList<T> newList = new ArrayList<T>();
		
		while (!copy.isEmpty()) {
			
			newList.add(copy.remove(rand.nextInt(copy.size())));
			
		}
		
		return newList;
		
	}
	
}
