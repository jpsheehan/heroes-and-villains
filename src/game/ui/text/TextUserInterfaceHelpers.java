package game.ui.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class TextUserInterfaceHelpers {
	
	/**
	 * An array of Strings that mean "yes".
	 */
	private static String[] affirmativeStrings = new String[] {
		"yes",
		"y",
		"please"
	};
	
	/**
	 * An array of Strings that mean "no";
	 */
	private static String[] negativeStrings = new String[] {
		"no",
		"n",
		"nah",
		"nope"
	};
	
	/**
	 * Returns 2 if the input string is truthy, returns 1 if it's falsey and returns 0 if it's something else.
	 * @param input The String to test against
	 * @return
	 */
	private static int getYesNoUnknown(String input) {
		
		for (String str : affirmativeStrings) {
			if (str.equals(input))
				return 2;
		}
		
		for (String str : negativeStrings) {
			if (str.equals(input)) {
				return 1;
			}
		}
		
		return 0;
		
	}
	
	/**
	 * Shows a yes/no question to the user.
	 * @param question The question to prompt them with
	 * @return Returns true if the user said yes.
	 */
	public static boolean showYesNo(String question) {
		
		int choice = 0;
		
		// Print the question
		System.out.println(question);
		
		// Prompt for and read the user's input
		System.out.print("> ");
		String input = readLine().toLowerCase();
		
		// Keep asking while the input is invalid.
		while ((choice = getYesNoUnknown(input)) == 0) {
			
			System.out.print("> ");
			input = readLine().toLowerCase();
			
		}
		
		// Return the correct value
		return choice == 2;
		
	}
	
	/**
	 * Prompts the user to select an item from an array.
	 * @param message The message to show to the user.
	 * @param options The options to display.
	 * @return Returns the index of the Array that the user chose.
	 */
	public static int showChoice(String message, String[] options) {
		
		int choice;
		
		// Print the message
		System.out.println(message);
		
		// Print the options
		for (int i = 0; i < options.length; i++) {
			
			// Create a string that has a length of 4 with the number "right aligned"
			String numberString = padLeft(new Integer(i + 1).toString(), ' ', 4);
			
			// Print the option
			System.out.println(String.format("%s) %s", numberString, options[i]));
			
		}
		
		choice = getNumberWithBounds(1, options.length);
		
		// Return the index of the String array that the user chose.
		return choice - 1;
		
	}
	
	/**
	 * Returns one line of text from the standard input.
	 * @return
	 */
	public static String readLine() {
		
		// Create a new buffer of a particular size. This size denotes how many characters of input can be accepted.
		// 1024 is probably overkill and 32 would probably suffice.
		int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		
		try {
			
			// Read the bytes from the System.in
			int bytesRead = System.in.read(buffer);
			
			// Create a ByteBuffer from the byte array and convert to a UTF-8 CharBuffer
			ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, bytesRead);
			CharBuffer charBuffer = Charset.forName("UTF-8").decode(byteBuffer);
			
			// Return the resulting String
			return charBuffer.toString().trim();
			
		} catch (IOException e) {
			
			// If an error occurs, just return an empty String
			return "";
			
		}
		
	}
	
	/**
	 * Clears the screen.
	 */
	public static void clear() {
		// the terminal will likely be 25 lines high
		// TODO: find a reliable method for detecting console height
		System.out.println(repeatString("\n", 25));
	}
	
	/**
	 * Returns a copy of a String repeated n times.
	 * @param str The String to repeat.
	 * @param n The number of times to repeat the String.
	 * @return
	 */
	public static String repeatString(String str, Integer n) {
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			builder.append(str);
		}
		
		return builder.toString();
	}
	
	/**
	 * Prepends a String with a particular character until it is a certain length.
	 * @param str The String to pad.
	 * @param pad The padding character
	 * @param width The width of the desired String
	 * @return Returns a new padded String.
	 */
	public static String padLeft(String str, Character pad, int width) {
		
		int numberOfPads = width - str.length();
		
		return repeatString(pad.toString(), numberOfPads) + str;
		
	}
	
	/**
	 * Appends a String with a particular character until it is a certain length.
	 * @param str The String to pad.
	 * @param pad The padding character.
	 * @param width The width of the desired String.
	 * @return Returns a new padded String.
	 */
	public static String padRight(String str, Character pad, int width) {
		
		int numberOfPads = width - str.length();
		
		return str + repeatString(pad.toString(), numberOfPads);
	}
	
	
	/**
	 * Prompts the user to enter a number between min and max (inclusive).
	 * @param min The minimum bound (inclusive).
	 * @param max The maximum bound (inclusive).
	 * @return Returns the entered number.
	 */
	public static Integer getNumberWithBounds(Integer min, Integer max) {
		
		return getNumberWithBounds(min, max, "> ");
		
	}
	
	/**
	 * Prompts the user to enter a number between min and max (inclusive).
	 * @param min The minimum bound (inclusive).
	 * @param max The maximum bound (inclusive).
	 * @param prelude The prompt beginning each line.
	 * @return Returns the entered number.
	 */
	public static Integer getNumberWithBounds(Integer min, Integer max, String prelude) {
		
		int choice;
		
		// Check for any erroneous arguments
		if (min > max) {
			
			throw new IllegalArgumentException("min cannot be greater than max");
			
		}

		// Get the first input
		System.out.print(prelude);
		
		try {
		
			choice = Integer.parseInt(readLine());
			
		}
		catch (NumberFormatException err) {
			
			choice = min - 1;
			
		}
		
		// Keep getting input until it is valid.
		while (choice < min || choice > max) {
			
			System.out.print(prelude);
			
			try {
				
				choice = Integer.parseInt(readLine());
				
			}
			catch (NumberFormatException err) {
				
				choice = min - 1;
				
			}
		}
		
		return choice;
		
	}
	
	public static void main(String[] args) {
		
		String[] colours = new String[] { "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet", "Black", "Grey", "White" };
		
		int index = showChoice("Select your favourite colour:", colours);
		
		System.out.println("You selected " + (index + 1));
		System.out.println("colours[" + index + "] = \"" + colours[index] + "\"");
		
	}
}
