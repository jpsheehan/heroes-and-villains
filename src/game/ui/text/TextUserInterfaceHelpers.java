package game.ui.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class TextUserInterfaceHelpers {
	
	/**
	 * The width of the console.
	 */
	private static int consoleWidth = 80;
	
	/**
	 * The height of the console.
	 */
	private static int consoleHeight = 25;
	
	/**
	 * Set the console width.
	 * @param width The width.
	 */
	public static void setConsoleWidth(int width) {
		
		TextUserInterfaceHelpers.consoleWidth = width;
		
	}
	
	/**
	 * Set the console height.
	 * @param height The height.
	 */
	public static void setConsoleHeight(int height) {
		
		TextUserInterfaceHelpers.consoleHeight = height;
		
	}
	
	/**
	 * Gets the console width.
	 * @return The width of the console.
	 */
	public static int getConsoleWidth() {
		
		return TextUserInterfaceHelpers.consoleWidth;
		
	}

	/**
	 * Gets the console height.
	 * @return The height of the console.
	 */
	public static int getConsoleHeight() {
		
		return TextUserInterfaceHelpers.consoleHeight;
		
	}
	
	/**
	 * Prints the string centered in the console
	 * @param line The string to print.
	 */
	public static void printLineCentred(String line) {
		
		printLineCentredBordered(line, ' ');
		
	}
	
	/**
	 * Prints a horizontal rule with a default character. Defaults to '#'.
	 */
	public static void printHorizontalRule() {
		
		printHorizontalRule('=');
		
	}
	
	/**
	 * Print a horizontal rule with the specified character.
	 * @param rule The character to use.
	 */
	public static void printHorizontalRule(Character rule) {
		
		System.out.println(repeatString(rule.toString(), getConsoleWidth() - 1));
		
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
	
	/**
	 * Returns 2 if the input string is truthy, returns 1 if it's falsey and returns 0 if it's something else.
	 * @param input The String to test against
	 * @return
	 */
	private static int getYesNoUnknown(String input) {
		
		// Return 2 if the input is in the affirmativeStrings array
		for (String str : affirmativeStrings) {
			if (str.equals(input))
				return 2;
		}
		
		// Return 1 if the input is in the negativeStrings array
		for (String str : negativeStrings) {
			if (str.equals(input)) {
				return 1;
			}
		}
		
		// Otherwise return 0
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
		System.out.println(question + " (y/n)");
		
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
		return readLine("");
	}
	
	/**
	 * Returns one line of text from the standard input.
	 * @param prelude The text to place before entering something.
	 * @return
	 */
	public static String readLine(String prelude) {
		
		// Create a new buffer of a particular size. This size denotes how many characters of input can be accepted.
		// 1024 is probably overkill and 32 would probably suffice.
		int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		
		try {
			
			System.out.print(prelude);
			
			// Read the bytes from the System.in
			int bytesRead = System.in.read(buffer);
			
			// Return an empty string if we are at the end of the stream
			if (bytesRead == -1) {
				
				return "";
				
			}
			
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
		
		if (game.GeneralHelpers.isRunningInEclipse()) {
			
			// if the game is being run in eclipse, use this hack to "clear" the screen.
			System.out.println(repeatString("\n", getConsoleHeight()));
			
		} else {
		
			// otherwise assume we are running in a terminal
			System.out.print("\033[H\033[2J");  
			System.out.flush();
			
		}
		
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
	
	/**
	 * Prints a centered line with a border on the horizontal edges
	 * @param line The text to centre.
	 * @param border The character to print as the border.
	 */
	public static void printLineCentredBordered(String line, Character border) {
		
		int numberOfPads = (int)((getConsoleWidth() - line.length()) / 2.0) - 1;
		int evenPadFix = 0;
		
		// account for even length strings
		if (line.length() % 2 == 0) {
			
			evenPadFix -= 1;
			
		}
		
		System.out.println(
				border + repeatString(" ", numberOfPads) + 
				line + repeatString(" ", numberOfPads + evenPadFix) + 
				border);
		
	}
	
	/**
	 * Prints a title block.
	 * @param title The string to use as the title.
	 * @param border The border character.
	 */
	public static void printTitleBlock(String title, Character border) {
		
		printTitleBlock(new String[] {title}, border);
		
		
	}
	
	/**
	 * Prints a title block.
	 * @param titles The string array of titles.
	 * @param border The border character.
	 */
	public static void printTitleBlock(String[] titles, Character border) {
		
		clear();
		printHorizontalRule(border);
		
		for (String title : titles) {
			
			printLineCentredBordered(title, border);
			
		}
		
		printHorizontalRule(border);
		System.out.println();
		
	}
	
}
