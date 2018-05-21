package game.ui.text;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * A class containing only static helper methods for creating text-based user interfaces.
 * I recommend import like so:
 * 
 * 		import static game.ui.text.TextUserInterfaceHelpers.*;
 * 
 * @author jesse
 *
 */
public class TextUserInterfaceHelpers {
	
	/**
	 * Contains any remaining input from the readLine method.
	 */
	private static String leftoverInput = "";
	
	private static InputStream previousStdIn = null;
	
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
		
		if (width <= 0) {
			
			throw new IllegalArgumentException("Width must be positive.");
			
		}
		
		TextUserInterfaceHelpers.consoleWidth = width;
		
	}
	
	/**
	 * Set the console height.
	 * @param height The height.
	 */
	public static void setConsoleHeight(int height) {
		
		if (height <= 0) {
			
			throw new IllegalArgumentException("Height must be positive.");
			
		}
		
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
	 * Shows a yes/no question to the user.
	 * @param question The question to prompt them with.
	 * @return True if the user said yes.
	 * @throws UserCancelException 
	 * @throws UserQuitException 
	 */
	public static boolean showYesNo(String question) throws UserQuitException, UserCancelException {
		
		return showYesNo(question, false);
		
	}
	
	/**
	 * Shows a yes/no question to the user.
	 * @param question The question to prompt them with
	 * @param suppressQuit Used internally for the readLine method.
	 * @return Returns true if the user said yes.
	 * @throws UserQuitException 
	 * @throws UserCancelException 
	 */
	protected static boolean showYesNo(String question, boolean suppressQuit) throws UserQuitException, UserCancelException {
		
		Affirmation choice = Affirmation.UNKNOWN;
		String input = "";
		
		// Print the question
		System.out.println(question + " " + getInputOptions(new String[] {"y", "n"}));
		
		// Prompt for and read the user's input
		System.out.print("> ");
		
		// Handle UserQuitException and UserCancelException appropriately.
		// Rethrow them if suppressQuit is false, otherwise return false.
		try {
			
			input = readLine().toLowerCase();
			
		} catch (UserQuitException e) {
			
			if (suppressQuit) {
				
				return false;
				
			}
			
			throw e;
			
		} catch (UserCancelException e) {
			
			if (suppressQuit) {
				
				return false;
				
			}
			
			throw e;
			
		} catch (UserContinueException e) {
			
			// Print the question again
			System.out.println(question + " " + getInputOptions(new String[] {"y", "n"}));
			
		}
		
		// Keep asking while the input is invalid.
		while ((choice = Affirmation.fromString(input)) == Affirmation.UNKNOWN) {
			
			System.out.print("> ");
			
			// Handle the exceptions again
			try {
				
				input = readLine().toLowerCase();
				
			} catch (UserQuitException e) {
				
				if (suppressQuit) {
					
					return false;
					
				}
				
				throw e;
				
			} catch (UserCancelException e) {
				
				if (suppressQuit) {
					
					return false;
					
				}
				
				throw e;
				
			} catch (UserContinueException e) {
				
				// Print the question again
				System.out.println(question + " " + getInputOptions(new String[] {"y", "n"}));
				
			}
			
		}
		
		// Return the correct value
		return choice == Affirmation.YES;
		
	}
	
	/**
	 * Prompts the user to select an item from an array.
	 * @param message The message to show to the user.
	 * @param options The options to display.
	 * @return Returns the index of the Array that the user chose.
	 * @throws UserQuitException 
	 * @throws UserCancelException 
	 */
	public static int showChoice(String message, String[] options) throws UserCancelException, UserQuitException {
		
		int choice = 0;
		
		// Print the message
		System.out.println(message + " " + getInputOptions("1-" + options.length));
		
		// Print the options
		for (int i = 0; i < options.length; i++) {
			
			// Create a string that has a length of 4 with the number "right aligned"
			String numberString = padLeft(new Integer(i + 1).toString(), ' ', 4);
			
			// Print the option
			System.out.println(String.format("%s) %s", numberString, options[i]));
			
		}
		
		while (true) {
			
			try {
				
				choice = getNumberWithBounds(1, options.length);
				break;
				
			} catch (UserContinueException e) {
				
				// Print the message again
				System.out.println(message + " " + getInputOptions("1-" + options.length));
				
			}
		}
		
		// Return the index of the String array that the user chose.
		return choice - 1;
		
	}
	
	/**
	 * Returns one line of text from the standard input.
	 * @return
	 * @throws UserQuitException 
	 * @throws UserCancelException 
	 * @throws UserContinueException 
	 */
	public static String readLine() throws UserCancelException, UserQuitException, UserContinueException {
		return readLine("");
	}
	
	/**
	 * Returns one line of text from the standard input.
	 * @param prelude The text to place before entering something.
	 * @return
	 * @throws UserCancelException 
	 * @throws UserQuitException 
	 * @throws UserContinueException 
	 */
	public static String readLine(String prelude) throws UserCancelException, UserQuitException, UserContinueException {
		
		// Check whether System.in has been changed. If so, clear the leftovers.
		if (TextUserInterfaceHelpers.previousStdIn == null) {
			
			TextUserInterfaceHelpers.previousStdIn = System.in;
			
		} else {
			
			if (TextUserInterfaceHelpers.previousStdIn != System.in) {
				
				TextUserInterfaceHelpers.previousStdIn = System.in;
				TextUserInterfaceHelpers.leftoverInput = "";
				
			}
			
		}
		
		// pull the input from the leftovers if possible
		if (!TextUserInterfaceHelpers.leftoverInput.isEmpty()) {
			
			String[] lines = TextUserInterfaceHelpers.leftoverInput.split("\n", 2);
			String input;
			
			switch (lines[0].toLowerCase()) {
			
				case "c":
					// user wants to cancel
					
					throw new UserCancelException();
				
				case "q":
					// user might want to quit
					
					// set any leftover input so that it can be grabbed by the showYesNo method
					if (lines.length == 2) {
						
						TextUserInterfaceHelpers.leftoverInput = lines[1];
						
					}
					
					boolean shouldQuit = showYesNo("Are you sure you want to quit?", true);
					
					if (shouldQuit) {
						
						throw new UserQuitException();
						
					} else {
					
						throw new UserContinueException();
						
					}
				
				default:
					
					if (lines.length == 2) {
						
						// store the leftovers and set the input to the first line
						TextUserInterfaceHelpers.leftoverInput = lines[1];
						input = lines[0];
						
					} else {
						
						// clear the leftovers
						TextUserInterfaceHelpers.leftoverInput = "";
						input = lines[0];
						
					}
					
					return input;
				
			}
			
		}
		
		// Create a new buffer of a particular size. This size denotes how many characters of input can be accepted.
		// 1024 is probably overkill and 32 would probably suffice.
		int BUFFER_SIZE = 1024;
		byte[] buffer = new byte[BUFFER_SIZE];
		
		String input;
		
		try {
			
			System.out.print(prelude);
			
			// wait a little bit for new data to become available
			// this is required by the TextGui
			while (System.in.available() == 0) {
				
				Thread.yield();
				
			}
			
			// Read the bytes from the System.in
			int bytesRead = System.in.read(buffer);
			
			// End of the stream
			if (bytesRead == -1) {
				
				// clear and return any leftover string
				input = TextUserInterfaceHelpers.leftoverInput;
				TextUserInterfaceHelpers.leftoverInput = "";
				
			} else {
				
				// Create a ByteBuffer from the byte array and convert to a UTF-8 CharBuffer
				ByteBuffer byteBuffer = ByteBuffer.wrap(buffer, 0, bytesRead);
				CharBuffer charBuffer = Charset.forName("UTF-8").decode(byteBuffer);
				
				input = charBuffer.toString().trim();
				
				// prepend any leftover data to the input
				input = TextUserInterfaceHelpers.leftoverInput + input;
				
			}
			
			String[] lines = input.split("\n", 2);
			
			switch (lines[0].toLowerCase()) {
			
				case "c":
					// user wants to cancel
					
					throw new UserCancelException();
				
				case "q":
					// user might want to quit
					
					// set any leftover input so that it can be grabbed by the showYesNo method
					if (lines.length == 2) {
						
						TextUserInterfaceHelpers.leftoverInput = lines[1];
						
					}
					
					boolean shouldQuit = showYesNo("Are you sure you want to quit?", true);
					
					if (shouldQuit) {
						
						throw new UserQuitException();
						
					} else {
					
						throw new UserContinueException();
						
					}
				
				default:
					
					if (lines.length == 2) {
						
						// store the leftovers and set the input to the first line
						TextUserInterfaceHelpers.leftoverInput = lines[1];
						input = lines[0];
						
					} else {
						
						// clear the leftovers
						TextUserInterfaceHelpers.leftoverInput = "";
						input = lines[0];
						
					}
					
					return input;
				
			}
			
		} catch (IOException e) {
			
			// If an error occurs, just return an empty String
			return "";
			
		}
		
	}
	
	/**
	 * Clears the screen.
	 */
	public static void clear() {
		
		// Use this hack to "clear" the screen.
		System.out.println(repeatString("\n", getConsoleHeight()));
		
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
	 * @throws UserQuitException 
	 * @throws UserCancelException 
	 * @throws UserContinueException 
	 */
	public static Integer getNumberWithBounds(Integer min, Integer max) throws UserCancelException, UserQuitException, UserContinueException {
		
		return getNumberWithBounds(min, max, "> ");
		
	}
	
	/**
	 * Prompts the user to enter a number between min and max (inclusive).
	 * @param min The minimum bound (inclusive).
	 * @param max The maximum bound (inclusive).
	 * @param prelude The prompt beginning each line.
	 * @return Returns the entered number.
	 * @throws UserQuitException 
	 * @throws UserCancelException 
	 * @throws UserContinueException 
	 */
	public static Integer getNumberWithBounds(Integer min, Integer max, String prelude) throws UserCancelException, UserQuitException, UserContinueException {
		
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
	 */
	public static void printTitleBlock(String title) {
		
		printTitleBlock(title, '#');
		
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
	 * @param titles The string array to use as the titles.
	 */
	public static void printTitleBlock(String[] titles) {
		
		printTitleBlock(titles, '#');
		
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
	
	/**
	 * Returns a string containing all the input options.
	 * @param option
	 * @return The string containing all the input options including cancel and quit.
	 */
	public static String getInputOptions(String option) {
		
		return getInputOptions(new String[] { option });
		
	}
	
	/**
	 * Returns the input options available for this input dialog.
	 * @param options A string list of input options.
	 * @return Returns a string containing all the options for this input dialog (including quit and cancel).
	 */
	public static String getInputOptions(String[] options) {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("(");
		builder.append(String.join(", ", options));
		
		if (options.length > 0) {
			
			builder.append(", ");
			
		}
		
		builder.append("c, q");
		builder.append(")");
		return builder.toString();
		
	}
	
	/**
	 * Displays a message.
	 * @param message The message to show.
	 * @throws UserQuitException If the user quits.
	 */
	public static void showMessageDialog(String message) throws UserQuitException {
		
		showMessageDialog(message, "INFORMATION");
		
	}
	
	/**
	 * Displays a message.
	 * @param message The message to show.
	 * @param title The title to show.
	 * @throws UserQuitException If the user quits.
	 */
	public static void showMessageDialog(String message, String title) throws UserQuitException {
		
		printTitleBlock(title);
		
		printLineCentred(message);
		
		System.out.println();
		
		try {
			
			printLineCentred("Press <Enter> to continue...");
			readLine("");
			
		} catch (UserCancelException | UserContinueException e) {
			
		}
		
	}
	
	public static String showInputDialog(String prompt, String title) throws UserQuitException {
		
		printTitleBlock(title);
		
		String input = "";
		
		while (input.equals("")) {
			
			System.out.println(prompt);
			
			try {
				
				input = readLine("> ");
				
			} catch (UserCancelException e) {
				
				return "";
				
			} catch (UserContinueException e) {
				
				return showInputDialog(prompt, title);
				
			}
			
		}
		
		return input;
		
	}
	
	public static boolean showYesNoDialog(String prompt, String title) throws UserQuitException {
		
		while (true) {
			
			printTitleBlock(title);
			
			try {
				
				return showYesNo(prompt);
				
			} catch (UserCancelException e) {
				
				continue;
				
			}
			
		}
		
	}
}
