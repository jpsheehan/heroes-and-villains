package game.ui.text;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class TextUserInterfaceHelpers {
	
	public static void showMessage(String message) {
		
		System.out.println(message);
		
	}
	
	public static boolean showYesNo(String question) {
		return false;
	}
	
	/**
	 * Prompts the user to select an item from an array.
	 * @param message The message to show to the user.
	 * @param options The options to display.
	 * @return Returns the index of the Array that the user chose.
	 */
	public static int showChoice(String message, String[] options) {
		
		// Print the message
		System.out.println(message);
		
		// Print the options
		for (int i = 0; i < options.length; i++) {
			
			// Create a string that has a length of 4 with the number "right aligned"
			String numberString = padLeft(new Integer(i + 1).toString(), ' ', 4);
			
			// Print the option
			System.out.println(String.format("%s) %s", numberString, options[i]));
			
		}
		
		// Prompt for and read the user's choice
		System.out.print("> ");
		int choice = Integer.parseInt(readLine());
		
		// While the user's choice is invalid, keep asking them
		while (choice < 1 || choice > options.length) {
			
			System.out.print("> ");
			choice = Integer.parseInt(readLine());
			
		}
		
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
	
	public static String padRight(String str, Character pad, int width) {
		
		int numberOfPads = width - str.length();
		
		return str + repeatString(pad.toString(), numberOfPads);
	}
	
	public static void main(String[] args) {
		
		String[] options = new String[] {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
		
		int choice = showChoice("What is your favourite number?", options);
		
		System.out.println(choice);
		
	}
	
}
