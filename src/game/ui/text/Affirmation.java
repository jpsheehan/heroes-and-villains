package game.ui.text;

/**
 * Represents a yes/no/other choice.
 * @author jps111
 *
 */
public enum Affirmation {

	YES,
	NO,
	UNKNOWN;
	
	/**
	 * Converts an yes/no string into an affirmation.
	 * @param input The string to test against.
	 * @return The result of this affirmation test.
	 */
	public static Affirmation fromString(String input) {
		
		// take the lower case of the input.
		input = input.toLowerCase();
		
		// Return 2 if the input is in the affirmativeStrings array
		for (String str : affirmativeStrings) {
			if (str.equals(input))
				return Affirmation.YES;
		}
		
		// Return 1 if the input is in the negativeStrings array
		for (String str : negativeStrings) {
			if (str.equals(input)) {
				return Affirmation.NO;
			}
		}
		
		// Otherwise return 0
		return Affirmation.UNKNOWN;
		
	}
	
	/**
	 * An array of Strings that mean "yes".
	 */
	private static String[] affirmativeStrings = new String[] {
		"yes",
		"y",
		"1",
		"yup",
		"yeah"
	};
	
	/**
	 * An array of Strings that mean "no";
	 */
	private static String[] negativeStrings = new String[] {
		"no",
		"n",
		"0",
		"nope",
		"nah"
	};
	
}
